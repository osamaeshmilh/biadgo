import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IRestaurantZonePrice} from '../restaurant-zone-price.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../restaurant-zone-price.test-samples';

import {RestaurantZonePriceService} from './restaurant-zone-price.service';

const requireRestSample: IRestaurantZonePrice = {
  ...sampleWithRequiredData,
};

describe('RestaurantZonePrice Service', () => {
  let service: RestaurantZonePriceService;
  let httpMock: HttpTestingController;
  let expectedResult: IRestaurantZonePrice | IRestaurantZonePrice[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RestaurantZonePriceService);
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

    it('should create a RestaurantZonePrice', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const restaurantZonePrice = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(restaurantZonePrice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RestaurantZonePrice', () => {
      const restaurantZonePrice = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(restaurantZonePrice).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RestaurantZonePrice', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RestaurantZonePrice', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RestaurantZonePrice', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addRestaurantZonePriceToCollectionIfMissing', () => {
      it('should add a RestaurantZonePrice to an empty array', () => {
        const restaurantZonePrice: IRestaurantZonePrice = sampleWithRequiredData;
        expectedResult = service.addRestaurantZonePriceToCollectionIfMissing([], restaurantZonePrice);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantZonePrice);
      });

      it('should not add a RestaurantZonePrice to an array that contains it', () => {
        const restaurantZonePrice: IRestaurantZonePrice = sampleWithRequiredData;
        const restaurantZonePriceCollection: IRestaurantZonePrice[] = [
          {
            ...restaurantZonePrice,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRestaurantZonePriceToCollectionIfMissing(restaurantZonePriceCollection, restaurantZonePrice);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RestaurantZonePrice to an array that doesn't contain it", () => {
        const restaurantZonePrice: IRestaurantZonePrice = sampleWithRequiredData;
        const restaurantZonePriceCollection: IRestaurantZonePrice[] = [sampleWithPartialData];
        expectedResult = service.addRestaurantZonePriceToCollectionIfMissing(restaurantZonePriceCollection, restaurantZonePrice);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantZonePrice);
      });

      it('should add only unique RestaurantZonePrice to an array', () => {
        const restaurantZonePriceArray: IRestaurantZonePrice[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const restaurantZonePriceCollection: IRestaurantZonePrice[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantZonePriceToCollectionIfMissing(restaurantZonePriceCollection, ...restaurantZonePriceArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const restaurantZonePrice: IRestaurantZonePrice = sampleWithRequiredData;
        const restaurantZonePrice2: IRestaurantZonePrice = sampleWithPartialData;
        expectedResult = service.addRestaurantZonePriceToCollectionIfMissing([], restaurantZonePrice, restaurantZonePrice2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantZonePrice);
        expect(expectedResult).toContain(restaurantZonePrice2);
      });

      it('should accept null and undefined values', () => {
        const restaurantZonePrice: IRestaurantZonePrice = sampleWithRequiredData;
        expectedResult = service.addRestaurantZonePriceToCollectionIfMissing([], null, restaurantZonePrice, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantZonePrice);
      });

      it('should return initial array if no RestaurantZonePrice is added', () => {
        const restaurantZonePriceCollection: IRestaurantZonePrice[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantZonePriceToCollectionIfMissing(restaurantZonePriceCollection, undefined, null);
        expect(expectedResult).toEqual(restaurantZonePriceCollection);
      });
    });

    describe('compareRestaurantZonePrice', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRestaurantZonePrice(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareRestaurantZonePrice(entity1, entity2);
        const compareResult2 = service.compareRestaurantZonePrice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareRestaurantZonePrice(entity1, entity2);
        const compareResult2 = service.compareRestaurantZonePrice(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareRestaurantZonePrice(entity1, entity2);
        const compareResult2 = service.compareRestaurantZonePrice(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
