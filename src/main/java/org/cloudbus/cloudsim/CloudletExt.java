package org.cloudbus.cloudsim;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.cloudbus.cloudsim.core.CloudSim;

/**
 *
 * @author Admin
 */
public class CloudletExt extends Cloudlet {

    private double responseTime;

    public CloudletExt(final int cloudletId,
            final long cloudletLength,
            final int pesNumber,
            final long cloudletFileSize,
            final long cloudletOutputSize,
            final UtilizationModel utilizationModelCpu,
            final UtilizationModel utilizationModelRam,
            final UtilizationModel utilizationModelBw) {
        super(
                cloudletId,
                cloudletLength,
                pesNumber,
                cloudletFileSize,
                cloudletOutputSize,
                utilizationModelCpu,
                utilizationModelRam,
                utilizationModelBw,
                false);
    }

    public double getResponseTime() {
        return this.getFinishTime() - this.getExecStartTime();
    }

}
