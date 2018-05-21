import { ProposalAlgoModule } from './proposal-algo.module';

describe('ProposalAlgoModule', () => {
    let formModule: ProposalAlgoModule;

    beforeEach(() => {
        formModule = new ProposalAlgoModule();
    });

    it('should create an instance', () => {
        expect(formModule).toBeTruthy();
    });
});
