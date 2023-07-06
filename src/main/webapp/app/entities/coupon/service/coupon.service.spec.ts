import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {DATE_FORMAT} from 'app/config/input.constants';
import {ICoupon} from '../coupon.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../coupon.test-samples';

import {CouponService, RestCoupon} from './coupon.service';

const requireRestSample: RestCoupon = {
  ...sampleWithRequiredData,
  expiryDate: sampleWithRequiredData.expiryDate?.format(DATE_FORMAT),
};

describe('Coupon Service', () => {
  let service: CouponService;
  let httpMock: HttpTestingController;
  let expectedResult: ICoupon | ICoupon[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CouponService);
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

    it('should create a Coupon', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const coupon = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(coupon).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Coupon', () => {
      const coupon = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(coupon).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Coupon', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Coupon', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Coupon', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addCouponToCollectionIfMissing', () => {
      it('should add a Coupon to an empty array', () => {
        const coupon: ICoupon = sampleWithRequiredData;
        expectedResult = service.addCouponToCollectionIfMissing([], coupon);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(coupon);
      });

      it('should not add a Coupon to an array that contains it', () => {
        const coupon: ICoupon = sampleWithRequiredData;
        const couponCollection: ICoupon[] = [
          {
            ...coupon,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCouponToCollectionIfMissing(couponCollection, coupon);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Coupon to an array that doesn't contain it", () => {
        const coupon: ICoupon = sampleWithRequiredData;
        const couponCollection: ICoupon[] = [sampleWithPartialData];
        expectedResult = service.addCouponToCollectionIfMissing(couponCollection, coupon);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(coupon);
      });

      it('should add only unique Coupon to an array', () => {
        const couponArray: ICoupon[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const couponCollection: ICoupon[] = [sampleWithRequiredData];
        expectedResult = service.addCouponToCollectionIfMissing(couponCollection, ...couponArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const coupon: ICoupon = sampleWithRequiredData;
        const coupon2: ICoupon = sampleWithPartialData;
        expectedResult = service.addCouponToCollectionIfMissing([], coupon, coupon2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(coupon);
        expect(expectedResult).toContain(coupon2);
      });

      it('should accept null and undefined values', () => {
        const coupon: ICoupon = sampleWithRequiredData;
        expectedResult = service.addCouponToCollectionIfMissing([], null, coupon, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(coupon);
      });

      it('should return initial array if no Coupon is added', () => {
        const couponCollection: ICoupon[] = [sampleWithRequiredData];
        expectedResult = service.addCouponToCollectionIfMissing(couponCollection, undefined, null);
        expect(expectedResult).toEqual(couponCollection);
      });
    });

    describe('compareCoupon', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCoupon(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareCoupon(entity1, entity2);
        const compareResult2 = service.compareCoupon(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareCoupon(entity1, entity2);
        const compareResult2 = service.compareCoupon(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareCoupon(entity1, entity2);
        const compareResult2 = service.compareCoupon(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
