import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IDriverWallet} from '../driver-wallet.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../driver-wallet.test-samples';

import {DriverWalletService} from './driver-wallet.service';

const requireRestSample: IDriverWallet = {
  ...sampleWithRequiredData,
};

describe('DriverWallet Service', () => {
  let service: DriverWalletService;
  let httpMock: HttpTestingController;
  let expectedResult: IDriverWallet | IDriverWallet[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DriverWalletService);
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

    it('should create a DriverWallet', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const driverWallet = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(driverWallet).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DriverWallet', () => {
      const driverWallet = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(driverWallet).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DriverWallet', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DriverWallet', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DriverWallet', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addDriverWalletToCollectionIfMissing', () => {
      it('should add a DriverWallet to an empty array', () => {
        const driverWallet: IDriverWallet = sampleWithRequiredData;
        expectedResult = service.addDriverWalletToCollectionIfMissing([], driverWallet);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(driverWallet);
      });

      it('should not add a DriverWallet to an array that contains it', () => {
        const driverWallet: IDriverWallet = sampleWithRequiredData;
        const driverWalletCollection: IDriverWallet[] = [
          {
            ...driverWallet,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDriverWalletToCollectionIfMissing(driverWalletCollection, driverWallet);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DriverWallet to an array that doesn't contain it", () => {
        const driverWallet: IDriverWallet = sampleWithRequiredData;
        const driverWalletCollection: IDriverWallet[] = [sampleWithPartialData];
        expectedResult = service.addDriverWalletToCollectionIfMissing(driverWalletCollection, driverWallet);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(driverWallet);
      });

      it('should add only unique DriverWallet to an array', () => {
        const driverWalletArray: IDriverWallet[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const driverWalletCollection: IDriverWallet[] = [sampleWithRequiredData];
        expectedResult = service.addDriverWalletToCollectionIfMissing(driverWalletCollection, ...driverWalletArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const driverWallet: IDriverWallet = sampleWithRequiredData;
        const driverWallet2: IDriverWallet = sampleWithPartialData;
        expectedResult = service.addDriverWalletToCollectionIfMissing([], driverWallet, driverWallet2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(driverWallet);
        expect(expectedResult).toContain(driverWallet2);
      });

      it('should accept null and undefined values', () => {
        const driverWallet: IDriverWallet = sampleWithRequiredData;
        expectedResult = service.addDriverWalletToCollectionIfMissing([], null, driverWallet, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(driverWallet);
      });

      it('should return initial array if no DriverWallet is added', () => {
        const driverWalletCollection: IDriverWallet[] = [sampleWithRequiredData];
        expectedResult = service.addDriverWalletToCollectionIfMissing(driverWalletCollection, undefined, null);
        expect(expectedResult).toEqual(driverWalletCollection);
      });
    });

    describe('compareDriverWallet', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDriverWallet(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareDriverWallet(entity1, entity2);
        const compareResult2 = service.compareDriverWallet(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareDriverWallet(entity1, entity2);
        const compareResult2 = service.compareDriverWallet(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareDriverWallet(entity1, entity2);
        const compareResult2 = service.compareDriverWallet(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
