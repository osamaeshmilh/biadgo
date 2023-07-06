import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {DATE_FORMAT} from 'app/config/input.constants';
import {IReferral} from '../referral.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../referral.test-samples';

import {ReferralService, RestReferral} from './referral.service';

const requireRestSample: RestReferral = {
  ...sampleWithRequiredData,
  expiryDate: sampleWithRequiredData.expiryDate?.format(DATE_FORMAT),
  usedDateTime: sampleWithRequiredData.usedDateTime?.toJSON(),
};

describe('Referral Service', () => {
  let service: ReferralService;
  let httpMock: HttpTestingController;
  let expectedResult: IReferral | IReferral[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ReferralService);
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

    it('should create a Referral', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const referral = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(referral).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Referral', () => {
      const referral = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(referral).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Referral', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Referral', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Referral', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addReferralToCollectionIfMissing', () => {
      it('should add a Referral to an empty array', () => {
        const referral: IReferral = sampleWithRequiredData;
        expectedResult = service.addReferralToCollectionIfMissing([], referral);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(referral);
      });

      it('should not add a Referral to an array that contains it', () => {
        const referral: IReferral = sampleWithRequiredData;
        const referralCollection: IReferral[] = [
          {
            ...referral,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReferralToCollectionIfMissing(referralCollection, referral);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Referral to an array that doesn't contain it", () => {
        const referral: IReferral = sampleWithRequiredData;
        const referralCollection: IReferral[] = [sampleWithPartialData];
        expectedResult = service.addReferralToCollectionIfMissing(referralCollection, referral);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(referral);
      });

      it('should add only unique Referral to an array', () => {
        const referralArray: IReferral[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const referralCollection: IReferral[] = [sampleWithRequiredData];
        expectedResult = service.addReferralToCollectionIfMissing(referralCollection, ...referralArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const referral: IReferral = sampleWithRequiredData;
        const referral2: IReferral = sampleWithPartialData;
        expectedResult = service.addReferralToCollectionIfMissing([], referral, referral2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(referral);
        expect(expectedResult).toContain(referral2);
      });

      it('should accept null and undefined values', () => {
        const referral: IReferral = sampleWithRequiredData;
        expectedResult = service.addReferralToCollectionIfMissing([], null, referral, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(referral);
      });

      it('should return initial array if no Referral is added', () => {
        const referralCollection: IReferral[] = [sampleWithRequiredData];
        expectedResult = service.addReferralToCollectionIfMissing(referralCollection, undefined, null);
        expect(expectedResult).toEqual(referralCollection);
      });
    });

    describe('compareReferral', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReferral(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareReferral(entity1, entity2);
        const compareResult2 = service.compareReferral(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareReferral(entity1, entity2);
        const compareResult2 = service.compareReferral(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareReferral(entity1, entity2);
        const compareResult2 = service.compareReferral(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
