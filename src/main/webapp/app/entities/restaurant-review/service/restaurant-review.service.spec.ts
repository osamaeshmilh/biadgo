import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IRestaurantReview} from '../restaurant-review.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../restaurant-review.test-samples';

import {RestaurantReviewService} from './restaurant-review.service';

const requireRestSample: IRestaurantReview = {
  ...sampleWithRequiredData,
};

describe('RestaurantReview Service', () => {
  let service: RestaurantReviewService;
  let httpMock: HttpTestingController;
  let expectedResult: IRestaurantReview | IRestaurantReview[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RestaurantReviewService);
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

    it('should create a RestaurantReview', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const restaurantReview = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(restaurantReview).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RestaurantReview', () => {
      const restaurantReview = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(restaurantReview).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RestaurantReview', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RestaurantReview', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RestaurantReview', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addRestaurantReviewToCollectionIfMissing', () => {
      it('should add a RestaurantReview to an empty array', () => {
        const restaurantReview: IRestaurantReview = sampleWithRequiredData;
        expectedResult = service.addRestaurantReviewToCollectionIfMissing([], restaurantReview);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantReview);
      });

      it('should not add a RestaurantReview to an array that contains it', () => {
        const restaurantReview: IRestaurantReview = sampleWithRequiredData;
        const restaurantReviewCollection: IRestaurantReview[] = [
          {
            ...restaurantReview,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRestaurantReviewToCollectionIfMissing(restaurantReviewCollection, restaurantReview);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RestaurantReview to an array that doesn't contain it", () => {
        const restaurantReview: IRestaurantReview = sampleWithRequiredData;
        const restaurantReviewCollection: IRestaurantReview[] = [sampleWithPartialData];
        expectedResult = service.addRestaurantReviewToCollectionIfMissing(restaurantReviewCollection, restaurantReview);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantReview);
      });

      it('should add only unique RestaurantReview to an array', () => {
        const restaurantReviewArray: IRestaurantReview[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const restaurantReviewCollection: IRestaurantReview[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantReviewToCollectionIfMissing(restaurantReviewCollection, ...restaurantReviewArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const restaurantReview: IRestaurantReview = sampleWithRequiredData;
        const restaurantReview2: IRestaurantReview = sampleWithPartialData;
        expectedResult = service.addRestaurantReviewToCollectionIfMissing([], restaurantReview, restaurantReview2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantReview);
        expect(expectedResult).toContain(restaurantReview2);
      });

      it('should accept null and undefined values', () => {
        const restaurantReview: IRestaurantReview = sampleWithRequiredData;
        expectedResult = service.addRestaurantReviewToCollectionIfMissing([], null, restaurantReview, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantReview);
      });

      it('should return initial array if no RestaurantReview is added', () => {
        const restaurantReviewCollection: IRestaurantReview[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantReviewToCollectionIfMissing(restaurantReviewCollection, undefined, null);
        expect(expectedResult).toEqual(restaurantReviewCollection);
      });
    });

    describe('compareRestaurantReview', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRestaurantReview(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareRestaurantReview(entity1, entity2);
        const compareResult2 = service.compareRestaurantReview(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareRestaurantReview(entity1, entity2);
        const compareResult2 = service.compareRestaurantReview(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareRestaurantReview(entity1, entity2);
        const compareResult2 = service.compareRestaurantReview(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
