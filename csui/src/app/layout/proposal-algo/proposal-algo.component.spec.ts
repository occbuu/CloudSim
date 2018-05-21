import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProposalAlgoComponent } from './proposal-algo.component';

describe('ProposalAlgoComponent', () => {
    let component: ProposalAlgoComponent;
    let fixture: ComponentFixture<ProposalAlgoComponent>;

    beforeEach(
        async(() => {
            TestBed.configureTestingModule({
                declarations: [ProposalAlgoComponent]
            }).compileComponents();
        })
    );

    beforeEach(() => {
        fixture = TestBed.createComponent(ProposalAlgoComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
