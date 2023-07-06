import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {ICustomerWallet} from '../customer-wallet.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../customer-wallet.test-samples';

import {CustomerWalletService} from './customer-wallet.service';

const requireRestSample: ICustomerWallet = {
  ...sampleWithRequiredData,
};

describe('CustomerWallet Service', () => {
  let service: CustomerWalletService;
  let httpMock: HttpTestingController;
  let expectedResult: ICustomerWallet | ICustomerWallet[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CustomerWalletService);
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

    it('should create a CustomerWallet', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const customerWallet = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(customerWallet).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CustomerWallet', () => {
      const customerWallet = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(customerWallet).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CustomerWallet', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CustomerWallet', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CustomerWallet', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addCustomerWalletToCollectionIfMissing', () => {
      it('should add a CustomerWallet to an empty array', () => {
        const customerWallet: ICustomerWallet = sampleWithRequiredData;
        expectedResult = service.addCustomerWalletToCollectionIfMissing([], customerWallet);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(customerWallet);
      });

      it('should not add a CustomerWallet to an array that contains it', () => {
        const customerWallet: ICustomerWallet = sampleWithRequiredData;
        const customerWalletCollection: ICustomerWallet[] = [
          {
            ...customerWallet,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCustomerWalletToCollectionIfMissing(customerWalletCollection, customerWallet);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CustomerWallet to an array that doesn't contain it", () => {
        const customerWallet: ICustomerWallet = sampleWithRequiredData;
        const customerWalletCollection: ICustomerWallet[] = [sampleWithPartialData];
        expectedResult = service.addCustomerWalletToCollectionIfMissing(customerWalletCollection, customerWallet);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(customerWallet);
      });

      it('should add only unique CustomerWallet to an array', () => {
        const customerWalletArray: ICustomerWallet[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const customerWalletCollection: ICustomerWallet[] = [sampleWithRequiredData];
        expectedResult = service.addCustomerWalletToCollectionIfMissing(customerWalletCollection, ...customerWalletArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const customerWallet: ICustomerWallet = sampleWithRequiredData;
        const customerWallet2: ICustomerWallet = sampleWithPartialData;
        expectedResult = service.addCustomerWalletToCollectionIfMissing([], customerWallet, customerWallet2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(customerWallet);
        expect(expectedResult).toContain(customerWallet2);
      });

      it('should accept null and undefined values', () => {
        const customerWallet: ICustomerWallet = sampleWithRequiredData;
        expectedResult = service.addCustomerWalletToCollectionIfMissing([], null, customerWallet, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(customerWallet);
      });

      it('should return initial array if no CustomerWallet is added', () => {
        const customerWalletCollection: ICustomerWallet[] = [sampleWithRequiredData];
        expectedResult = service.addCustomerWalletToCollectionIfMissing(customerWalletCollection, undefined, null);
        expect(expectedResult).toEqual(customerWalletCollection);
      });
    });

    describe('compareCustomerWallet', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCustomerWallet(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareCustomerWallet(entity1, entity2);
        const compareResult2 = service.compareCustomerWallet(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareCustomerWallet(entity1, entity2);
        const compareResult2 = service.compareCustomerWallet(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareCustomerWallet(entity1, entity2);
        const compareResult2 = service.compareCustomerWallet(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
