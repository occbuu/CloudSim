package com.hieu.demo.controller;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import org.cloudbus.cloudsim.CloudletExt;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.ArimaDatacenterBroker;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;

import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmExt;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.GaussianGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;

import com.hieu.results.*;
import com.hieu.requests.*;

@RestController
@RequestMapping("/arima")
public class NewAlgoController {
	private static List<CloudletExt> cloudletList;
	private static List<VmExt> vmlist;
	
	@RequestMapping(value = "/testpost", method = RequestMethod.POST)
	public ResponseEntity<?> startsim1(@RequestBody simRequest req) 
	{		
		SingleResult res = new SingleResult();
		res.setMessage("Arima testing...");
		res.setMessageEng("Arima testing...");
		res.setResult(req);
		        
		return new ResponseEntity<>(res, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/startsim", method = RequestMethod.POST)
	public ResponseEntity<?> startSim(@RequestBody simRequest req) {
		SingleResult res = new SingleResult();
		res.setMessage("Arima testing...");
		res.setMessageEng("Arima testing...");
		res.setResult(req);
		BrokerResult broRes = new BrokerResult();
		Date d = new Date();
        Log.printLine("At time ["+ d.toLocaleString()+"] :"+"Starting CloudSim With ARIMA...");
        try {
            // First step: Initialize the CloudSim package. It should be called
            // before creating any entities.
            int num_user = 1;   // number of grid users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;  // mean trace events

            // Initialize the CloudSim library
            CloudSim.init(num_user, calendar, trace_flag);

            // Second step: Create Datacenters
            //Datacenters are the resource providers in CloudSim. We need at list one of them to run a CloudSim simulation
            @SuppressWarnings("unused")
            Datacenter datacenter0 = createDatacenter("Datacenter_0");

            //Third step: Create Broker
            ArimaDatacenterBroker broker = createBroker();
            //GlobalBrokerEx globalBroker = new GlobalBrokerEx("GlobalBroker");
            int brokerId = broker.getId();

            //Fourth step: Create VMs and Cloudlets and send them to broker
            int vmQty = req.getVmsQty();
            vmlist = createVM(brokerId, vmQty); //creating 20 vms
            Log.printLine("*** Total Virtual machines: ["+vmQty+"]");
            int clQty = req.getCloudletQty();
            cloudletList = createCloudlet1(brokerId, clQty); 
            Log.printLine("*** Total cloudlets sent: ["+clQty+"]");

            broker.submitVmList(vmlist);
            broker.submitCloudletList(cloudletList);
            //broker.submitCloudletList(cloudletList1);
            // Fifth step: Starts the simulation
            CloudSim.startSimulation();

            // Final step: Print results when simulation is over
            List<CloudletExt> newList = broker.getCloudletReceivedList();
            List<VmExt> newListVm = broker.getVmList();
            //newList.addAll(globalBroker.getBroker().getCloudletReceivedList());
            CloudSim.stopSimulation();
            ////
            broRes.setBrokerID(broker.getId());
            broRes.setCloudlets(getCloudletList(newList));
            broRes.setPredictedRT(broker.getPredictedRT());
            broRes.setLastRT(broker.getLastRT());
            broRes.setVms(getVmList(newListVm));            
			
			res.setResult(broRes);
            printCloudletList(newList);
            printVmsList(newListVm);
            printBrokerRT(broker.getLastRT());
            Log.printLine("Broker 's predicted RT:" + broker.getPredictedRT());
            printAuthorInfo() ;
          
        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
        
		return new ResponseEntity<>(res, HttpStatus.OK);	
	}
	
	

	private static long getRandomNumberInRange(long min, long max) {
        Random random = new Random();
        return random.nextLong() % (max - min) + max;
    }

    private static long getRandomNum(int mean, int std) {
        Random rng = new MersenneTwisterRNG();
        GaussianGenerator cpuGen = new GaussianGenerator(mean, std, rng);
        return cpuGen.nextValue().longValue();
    }
    
    //////
    private static List<VmExt> createVM(int userId, int vms) {

        //Creates a container to store VMs. This list is passed to the broker later
        LinkedList<VmExt> list = new LinkedList<VmExt>();

        //VM Parameters
        long size = 10000; //image size (MB)
        int ram = 512; //vm memory (MB)
        int mips = 1000;
        long bw = 1000;
        int pesNumber = 1; //number of cpus
        String vmm = "Xen"; //VMM name

        //create VMs
        VmExt[] vm = new VmExt[vms];

        for (int i = 0; i < vms; i++) {
            vm[i] = new VmExt(i, userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared());
            //for creating a VM with a space shared scheduling policy for cloudlets:
            //vm[i] = Vm(i, userId, mips, pesNumber, ram, bw, size, priority, vmm, new CloudletSchedulerSpaceShared());

            list.add(vm[i]);
        }

        return list;
    }

    private static List<CloudletExt> createCloudlet(int userId, int cloudlets, int idBegin) {
        LinkedList<CloudletExt> list = new LinkedList<CloudletExt>();
        int id = idBegin;
        //cloudlet parameters
        //
        //Log.printLine("Test ===" + lenRan.nextValue().longValue());
        int pesNumber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        CloudletExt[] cloudlet = new CloudletExt[cloudlets];

        for (int i = 0; i < cloudlets; i++) {
            long length = getRandomNum(10000, 7000);
            long fileSize = getRandomNum(25000, 20000);
            long outputSize = getRandomNum(600, 150);
            cloudlet[i] = new CloudletExt(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            // setting the owner of these Cloudlets
            cloudlet[i].setUserId(userId);
            list.add(cloudlet[i]);
            id++;
        }

        return list;
    }
    
    private static List<CloudletExt> createCloudlet1(int userId, int cloudlets) {
        List<CloudletExt> list = new LinkedList<CloudletExt>();   
        //cloudlet parameters
        //
        //Log.printLine("Test ===" + lenRan.nextValue().longValue());
        int pesNumber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        CloudletExt[] cloudlet = new CloudletExt[cloudlets];

        for (int i = 0; i < cloudlets; i++) {
            long length = getRandomNumberInRange(70000, 100000);
            long fileSize = getRandomNumberInRange(15000, 25000);
            long outputSize = getRandomNumberInRange(1500, 4500);
            cloudlet[i] = new CloudletExt(i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            // setting the owner of these Cloudlets
            cloudlet[i].setUserId(userId);
            list.add(cloudlet[i]);          
        }

        return list;
    }


    private static Datacenter createDatacenter(String name) {

        // Here are the steps needed to create a PowerDatacenter:
        // 1. We need to create a list to store one or more
        //    Machines
        List<Host> hostList = new ArrayList<Host>();

        // 2. A Machine contains one or more PEs or CPUs/Cores. Therefore, should
        //    create a list to store these PEs before creating
        //    a Machine.
        List<Pe> peList1 = new ArrayList<Pe>();

        int mips = 1000;

        // 3. Create PEs and add these into the list.
        //for a quad-core machine, a list of 4 PEs is required:
        peList1.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating
        peList1.add(new Pe(1, new PeProvisionerSimple(mips)));
        peList1.add(new Pe(2, new PeProvisionerSimple(mips)));
        peList1.add(new Pe(3, new PeProvisionerSimple(mips)));

        //Another list, for a dual-core machine
        List<Pe> peList2 = new ArrayList<Pe>();

        peList2.add(new Pe(0, new PeProvisionerSimple(mips)));
        peList2.add(new Pe(1, new PeProvisionerSimple(mips)));

        //4. Create Hosts with its id and list of PEs and add them to the list of machines
        int hostId = 0;
        int ram = 2048; //host memory (MB)
        long storage = 1000000; //host storage
        int bw = 10000;

        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList1,
                        new VmSchedulerTimeShared(peList1)
                )
        ); // This is our first machine

        hostId++;
        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList2,
                        new VmSchedulerTimeShared(peList2)
                )
        ); // Second machine

        hostId++;
        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList2,
                        new VmSchedulerTimeShared(peList2)
                )
        ); // 3rd machine
        
        hostId++;
        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList2,
                        new VmSchedulerTimeShared(peList2)
                )
        ); // 4th machine
        
        hostId++;
        hostList.add(
                new Host(
                        hostId,
                        new RamProvisionerSimple(ram),
                        new BwProvisionerSimple(bw),
                        storage,
                        peList2,
                        new VmSchedulerTimeShared(peList2)
                )
        ); // 5h machine
        //To create a host with a space-shared allocation policy for PEs to VMs:
        //hostList.add(
        //		new Host(
        //			hostId,
        //			new CpuProvisionerSimple(peList1),
        //			new RamProvisionerSimple(ram),
        //			new BwProvisionerSimple(bw),
        //			storage,
        //			new VmSchedulerSpaceShared(peList1)
        //		)
        //	);
        //To create a host with a oportunistic space-shared allocation policy for PEs to VMs:
        //hostList.add(
        //		new Host(
        //			hostId,
        //			new CpuProvisionerSimple(peList1),
        //			new RamProvisionerSimple(ram),
        //			new BwProvisionerSimple(bw),
        //			storage,
        //			new VmSchedulerOportunisticSpaceShared(peList1)
        //		)
        //	);
        // 5. Create a DatacenterCharacteristics object that stores the
        //    properties of a data center: architecture, OS, list of
        //    Machines, allocation policy: time- or space-shared, time zone
        //    and its price (G$/Pe time unit).
        String arch = "x86";      // system architecture
        String os = "Linux";          // operating system
        String vmm = "Xen";
        double time_zone = 7.0;         // time zone this resource located
        double cost = 3.0;              // the cost of using processing in this resource
        double costPerMem = 0.05;		// the cost of using memory in this resource
        double costPerStorage = 0.1;	// the cost of using storage in this resource
        double costPerBw = 0.1;			// the cost of using bw in this resource
        LinkedList<Storage> storageList = new LinkedList<Storage>();	//we are not adding SAN devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);

        // 6. Finally, we need to create a PowerDatacenter object.
        Datacenter datacenter = null;
        try {
            datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datacenter;
    }

    //We strongly encourage users to develop their own broker policies, to submit vms and cloudlets according
    //to the specific rules of the simulated scenario
    private static ArimaDatacenterBroker createBroker() {
        ArimaDatacenterBroker broker = null;
        try {
            broker = new ArimaDatacenterBroker("Broker");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return broker;
    }
    
    private static void printCloudletList(List<CloudletExt> list) {
        int size = list.size();
        CloudletExt cloudlet;

        String indent = "    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Cloudlet ID" + indent + "STATUS" + indent
                + "Data center ID" + indent + "VM ID" + indent + indent + "Time" + indent
                + "Start Time" + indent + "Finish Time" + indent + "Response Time" + indent
                + "Cloudlet Length" + indent + "FileSize" + indent + "Output Size"
        );

        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            cloudlet = list.get(i);
            Log.print(indent + cloudlet.getCloudletId() + indent + indent);

            if (cloudlet.getCloudletStatus() == CloudletExt.SUCCESS) {
                Log.print("SUCCESS");
                Log.printLine(indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId()
                        + indent + indent + indent + dft.format(cloudlet.getActualCPUTime())
                        + indent + indent + dft.format(cloudlet.getExecStartTime())
                        + indent + indent + dft.format(cloudlet.getFinishTime())
                        + indent + indent + dft.format(cloudlet.getResponseTime())
                        + indent + indent + indent + dft.format(cloudlet.getCloudletLength())
                        + indent + indent + indent + dft.format(cloudlet.getCloudletFileSize())
                        + indent + indent + indent + dft.format(cloudlet.getCloudletOutputSize())
                );
            }
        }
    }

    private static void printVmsList(List<VmExt> list) {
        int size = list.size();
        VmExt vm;
        DecimalFormat dft = new DecimalFormat("###.##");
        for (int i = 0; i < size; i++) {
            vm = list.get(i);
            Log.printLine("##VM ID: " + vm.getId() + " with info: ");
            List<Double> lst = vm.getLastRT();
            String str = "RT list: [";
            for (Double num : lst) {
                str = str + dft.format(num) + ";";
            }
            str = str + "]";
            Log.printLine(str);
            Log.printLine("##VM ID" + vm.getId() + "  has predicted RT:[" + vm.getPredictedRT() + "]");
            Log.printLine("##VM ID" + vm.getId() + "  has served [" + lst.size() + "] requests");
        }
    }

    private static void printBrokerRT(List<Double> lst) {
        Log.printLine("---------------------------------------------- ");
        Log.printLine("Broker with ResponseTime info: ");
        DecimalFormat dft = new DecimalFormat("###.##");
        String str = "RT list: [";
        for (Double num : lst) {
            str = str + dft.format(num) + ";";
        }
        str = str + "]";
        Log.printLine(str);
        Log.printLine("Broker has served [" + lst.size() + "] Requests");
    }
    
    private static void printAuthorInfo() {
        Date d = new Date();
        Log.printLine();
        Log.printLine("---------------- AUTHOR INFOMATION------------------------------ ");
        Log.printLine("This is simulation of new porposal algorithm in workload balance using response time. This algorithm uses the predicted response time to balance the workload between vms. ");        
        Log.printLine("The ARIMA prediction is applied to predict the coming response time of next request.");        
        Log.printLine("Author: HAO LEE");        
        Log.printLine("Sim result was runn at time:  ["+ d.toLocaleString()+"]" );        
    }
    
    private static List<CloudletResult> getCloudletList(List<CloudletExt> list)
	{
		ArrayList<CloudletResult> res = new ArrayList<CloudletResult>();
		int size = list.size();
		CloudletExt cloudlet;
		
		for (int i = 0; i < size; i++) {			
			cloudlet = list.get(i);
			CloudletResult cl = new CloudletResult();
			cl.setCloudletID(cloudlet.getCloudletId());
			if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {	
				cl.setNo(i+1);
				cl.setStatus("SUCCESS");
				cl.setDataCenterID(cloudlet.getResourceId());
				cl.setVmID(cloudlet.getVmId());
				cl.setTime(cloudlet.getActualCPUTime());
				cl.setStartTime(cloudlet.getExecStartTime());
				cl.setFinishTime(cloudlet.getFinishTime());
				cl.setResponseTime(cloudlet.getFinishTime()-cloudlet.getSubmissionTime());
				cl.setExecutionTime (cloudlet.getFinishTime()-cloudlet.getExecStartTime());
			}
			res.add(cl);
		}
		
		return res;		
	}
    
    
    private static List<VmResult> getVmList(List<VmExt> list)
	{
		ArrayList<VmResult> res = new ArrayList<VmResult>();
		int size = list.size();
		VmExt vm;
		
		for (int i = 0; i < size; i++) {			
			vm = list.get(i);
			VmResult cl = new VmResult();	
			cl.setNo(i+1);
			cl.setVmID(vm.getId());
			cl.setVmm(vm.getVmm());
			cl.setBw(vm.getBw());
			cl.setMips(vm.getMips());
			cl.setRam(vm.getRam());
			cl.setLastRT(vm.getLastRT());
			cl.setPredictedRT(vm.getPredictedRT());
			cl.setTotalServedRequest(vm.getTotalServedRequest());
			res.add(cl);
		}
		
		return res;		
	}
}
