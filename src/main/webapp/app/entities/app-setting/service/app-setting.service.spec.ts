import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IAppSetting} from '../app-setting.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../app-setting.test-samples';

import {AppSettingService} from './app-setting.service';

const requireRestSample: IAppSetting = {
  ...sampleWithRequiredData,
};

describe('AppSetting Service', () => {
  let service: AppSettingService;
  let httpMock: HttpTestingController;
  let expectedResult: IAppSetting | IAppSetting[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AppSettingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a AppSetting', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const appSetting = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(appSetting).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AppSetting', () => {
      const appSetting = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(appSetting).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AppSetting', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AppSetting', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AppSetting', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addAppSettingToCollectionIfMissing', () => {
      it('should add a AppSetting to an empty array', () => {
        const appSetting: IAppSetting = sampleWithRequiredData;
        expectedResult = service.addAppSettingToCollectionIfMissing([], appSetting);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appSetting);
      });

      it('should not add a AppSetting to an array that contains it', () => {
        const appSetting: IAppSetting = sampleWithRequiredData;
        const appSettingCollection: IAppSetting[] = [
          {
            ...appSetting,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAppSettingToCollectionIfMissing(appSettingCollection, appSetting);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AppSetting to an array that doesn't contain it", () => {
        const appSetting: IAppSetting = sampleWithRequiredData;
        const appSettingCollection: IAppSetting[] = [sampleWithPartialData];
        expectedResult = service.addAppSettingToCollectionIfMissing(appSettingCollection, appSetting);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appSetting);
      });

      it('should add only unique AppSetting to an array', () => {
        const appSettingArray: IAppSetting[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const appSettingCollection: IAppSetting[] = [sampleWithRequiredData];
        expectedResult = service.addAppSettingToCollectionIfMissing(appSettingCollection, ...appSettingArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const appSetting: IAppSetting = sampleWithRequiredData;
        const appSetting2: IAppSetting = sampleWithPartialData;
        expectedResult = service.addAppSettingToCollectionIfMissing([], appSetting, appSetting2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(appSetting);
        expect(expectedResult).toContain(appSetting2);
      });

      it('should accept null and undefined values', () => {
        const appSetting: IAppSetting = sampleWithRequiredData;
        expectedResult = service.addAppSettingToCollectionIfMissing([], null, appSetting, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(appSetting);
      });

      it('should return initial array if no AppSetting is added', () => {
        const appSettingCollection: IAppSetting[] = [sampleWithRequiredData];
        expectedResult = service.addAppSettingToCollectionIfMissing(appSettingCollection, undefined, null);
        expect(expectedResult).toEqual(appSettingCollection);
      });
    });

    describe('compareAppSetting', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAppSetting(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareAppSetting(entity1, entity2);
        const compareResult2 = service.compareAppSetting(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareAppSetting(entity1, entity2);
        const compareResult2 = service.compareAppSetting(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareAppSetting(entity1, entity2);
        const compareResult2 = service.compareAppSetting(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
