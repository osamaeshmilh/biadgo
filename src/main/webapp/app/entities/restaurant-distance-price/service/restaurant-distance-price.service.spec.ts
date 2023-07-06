import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IRestaurantDistancePrice} from '../restaurant-distance-price.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../restaurant-distance-price.test-samples';

import {RestaurantDistancePriceService} from './restaurant-distance-price.service';

const requireRestSample: IRestaurantDistancePrice = {
  ...sampleWithRequiredData,
};

describe('RestaurantDistancePrice Service', () => {
  let service: RestaurantDistancePriceService;
  let httpMock: HttpTestingController;
  let expectedResult: IRestaurantDistancePrice | IRestaurantDistancePrice[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RestaurantDistancePriceService);
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

    it('should create a RestaurantDistancePrice', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const restaurantDistancePrice = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(restaurantDistancePrice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RestaurantDistancePrice', () => {
      const restaurantDistancePrice = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(restaurantDistancePrice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RestaurantDistancePrice', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RestaurantDistancePrice', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RestaurantDistancePrice', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addRestaurantDistancePriceToCollectionIfMissing', () => {
      it('should add a RestaurantDistancePrice to an empty array', () => {
        const restaurantDistancePrice: IRestaurantDistancePrice = sampleWithRequiredData;
        expectedResult = service.addRestaurantDistancePriceToCollectionIfMissing([], restaurantDistancePrice);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantDistancePrice);
      });

      it('should not add a RestaurantDistancePrice to an array that contains it', () => {
        const restaurantDistancePrice: IRestaurantDistancePrice = sampleWithRequiredData;
        const restaurantDistancePriceCollection: IRestaurantDistancePrice[] = [
          {
            ...restaurantDistancePrice,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRestaurantDistancePriceToCollectionIfMissing(
          restaurantDistancePriceCollection,
          restaurantDistancePrice
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RestaurantDistancePrice to an array that doesn't contain it", () => {
        const restaurantDistancePrice: IRestaurantDistancePrice = sampleWithRequiredData;
        const restaurantDistancePriceCollection: IRestaurantDistancePrice[] = [sampleWithPartialData];
        expectedResult = service.addRestaurantDistancePriceToCollectionIfMissing(
          restaurantDistancePriceCollection,
          restaurantDistancePrice
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantDistancePrice);
      });

      it('should add only unique RestaurantDistancePrice to an array', () => {
        const restaurantDistancePriceArray: IRestaurantDistancePrice[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const restaurantDistancePriceCollection: IRestaurantDistancePrice[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantDistancePriceToCollectionIfMissing(
          restaurantDistancePriceCollection,
          ...restaurantDistancePriceArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const restaurantDistancePrice: IRestaurantDistancePrice = sampleWithRequiredData;
        const restaurantDistancePrice2: IRestaurantDistancePrice = sampleWithPartialData;
        expectedResult = service.addRestaurantDistancePriceToCollectionIfMissing([], restaurantDistancePrice, restaurantDistancePrice2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantDistancePrice);
        expect(expectedResult).toContain(restaurantDistancePrice2);
      });

      it('should accept null and undefined values', () => {
        const restaurantDistancePrice: IRestaurantDistancePrice = sampleWithRequiredData;
        expectedResult = service.addRestaurantDistancePriceToCollectionIfMissing([], null, restaurantDistancePrice, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantDistancePrice);
      });

      it('should return initial array if no RestaurantDistancePrice is added', () => {
        const restaurantDistancePriceCollection: IRestaurantDistancePrice[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantDistancePriceToCollectionIfMissing(restaurantDistancePriceCollection, undefined, null);
        expect(expectedResult).toEqual(restaurantDistancePriceCollection);
      });
    });

    describe('compareRestaurantDistancePrice', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRestaurantDistancePrice(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareRestaurantDistancePrice(entity1, entity2);
        const compareResult2 = service.compareRestaurantDistancePrice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareRestaurantDistancePrice(entity1, entity2);
        const compareResult2 = service.compareRestaurantDistancePrice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareRestaurantDistancePrice(entity1, entity2);
        const compareResult2 = service.compareRestaurantDistancePrice(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
