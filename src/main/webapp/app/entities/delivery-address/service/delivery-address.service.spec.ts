import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IDeliveryAddress} from '../delivery-address.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../delivery-address.test-samples';

import {DeliveryAddressService} from './delivery-address.service';

const requireRestSample: IDeliveryAddress = {
  ...sampleWithRequiredData,
};

describe('DeliveryAddress Service', () => {
  let service: DeliveryAddressService;
  let httpMock: HttpTestingController;
  let expectedResult: IDeliveryAddress | IDeliveryAddress[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DeliveryAddressService);
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

    it('should create a DeliveryAddress', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const deliveryAddress = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(deliveryAddress).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DeliveryAddress', () => {
      const deliveryAddress = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(deliveryAddress).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DeliveryAddress', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DeliveryAddress', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DeliveryAddress', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addDeliveryAddressToCollectionIfMissing', () => {
      it('should add a DeliveryAddress to an empty array', () => {
        const deliveryAddress: IDeliveryAddress = sampleWithRequiredData;
        expectedResult = service.addDeliveryAddressToCollectionIfMissing([], deliveryAddress);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(deliveryAddress);
      });

      it('should not add a DeliveryAddress to an array that contains it', () => {
        const deliveryAddress: IDeliveryAddress = sampleWithRequiredData;
        const deliveryAddressCollection: IDeliveryAddress[] = [
          {
            ...deliveryAddress,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDeliveryAddressToCollectionIfMissing(deliveryAddressCollection, deliveryAddress);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DeliveryAddress to an array that doesn't contain it", () => {
        const deliveryAddress: IDeliveryAddress = sampleWithRequiredData;
        const deliveryAddressCollection: IDeliveryAddress[] = [sampleWithPartialData];
        expectedResult = service.addDeliveryAddressToCollectionIfMissing(deliveryAddressCollection, deliveryAddress);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(deliveryAddress);
      });

      it('should add only unique DeliveryAddress to an array', () => {
        const deliveryAddressArray: IDeliveryAddress[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const deliveryAddressCollection: IDeliveryAddress[] = [sampleWithRequiredData];
        expectedResult = service.addDeliveryAddressToCollectionIfMissing(deliveryAddressCollection, ...deliveryAddressArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const deliveryAddress: IDeliveryAddress = sampleWithRequiredData;
        const deliveryAddress2: IDeliveryAddress = sampleWithPartialData;
        expectedResult = service.addDeliveryAddressToCollectionIfMissing([], deliveryAddress, deliveryAddress2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(deliveryAddress);
        expect(expectedResult).toContain(deliveryAddress2);
      });

      it('should accept null and undefined values', () => {
        const deliveryAddress: IDeliveryAddress = sampleWithRequiredData;
        expectedResult = service.addDeliveryAddressToCollectionIfMissing([], null, deliveryAddress, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(deliveryAddress);
      });

      it('should return initial array if no DeliveryAddress is added', () => {
        const deliveryAddressCollection: IDeliveryAddress[] = [sampleWithRequiredData];
        expectedResult = service.addDeliveryAddressToCollectionIfMissing(deliveryAddressCollection, undefined, null);
        expect(expectedResult).toEqual(deliveryAddressCollection);
      });
    });

    describe('compareDeliveryAddress', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDeliveryAddress(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareDeliveryAddress(entity1, entity2);
        const compareResult2 = service.compareDeliveryAddress(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareDeliveryAddress(entity1, entity2);
        const compareResult2 = service.compareDeliveryAddress(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareDeliveryAddress(entity1, entity2);
        const compareResult2 = service.compareDeliveryAddress(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
