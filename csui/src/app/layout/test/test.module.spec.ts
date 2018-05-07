import { TestModule } from './test.module';

describe('TestModule', () => {
    let formModule: TestModule;

    beforeEach(() => {
        formModule = new TestModule();
    });

    it('should create an instance', () => {
        expect(formModule).toBeTruthy();
    });
});
