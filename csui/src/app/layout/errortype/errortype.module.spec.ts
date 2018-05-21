import { ErrortypeModule } from './errortype.module';

describe('ErrortypeModule', () => {
    let formModule: ErrortypeModule;

    beforeEach(() => {
        formModule = new ErrortypeModule();
    });

    it('should create an instance', () => {
        expect(formModule).toBeTruthy();
    });
});
