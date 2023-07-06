import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IFavoriteRestaurant} from '../favorite-restaurant.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../favorite-restaurant.test-samples';

import {FavoriteRestaurantService} from './favorite-restaurant.service';

const requireRestSample: IFavoriteRestaurant = {
  ...sampleWithRequiredData,
};

describe('FavoriteRestaurant Service', () => {
  let service: FavoriteRestaurantService;
  let httpMock: HttpTestingController;
  let expectedResult: IFavoriteRestaurant | IFavoriteRestaurant[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FavoriteRestaurantService);
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

    it('should create a FavoriteRestaurant', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const favoriteRestaurant = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(favoriteRestaurant).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FavoriteRestaurant', () => {
      const favoriteRestaurant = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(favoriteRestaurant).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FavoriteRestaurant', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FavoriteRestaurant', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FavoriteRestaurant', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addFavoriteRestaurantToCollectionIfMissing', () => {
      it('should add a FavoriteRestaurant to an empty array', () => {
        const favoriteRestaurant: IFavoriteRestaurant = sampleWithRequiredData;
        expectedResult = service.addFavoriteRestaurantToCollectionIfMissing([], favoriteRestaurant);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(favoriteRestaurant);
      });

      it('should not add a FavoriteRestaurant to an array that contains it', () => {
        const favoriteRestaurant: IFavoriteRestaurant = sampleWithRequiredData;
        const favoriteRestaurantCollection: IFavoriteRestaurant[] = [
          {
            ...favoriteRestaurant,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFavoriteRestaurantToCollectionIfMissing(favoriteRestaurantCollection, favoriteRestaurant);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FavoriteRestaurant to an array that doesn't contain it", () => {
        const favoriteRestaurant: IFavoriteRestaurant = sampleWithRequiredData;
        const favoriteRestaurantCollection: IFavoriteRestaurant[] = [sampleWithPartialData];
        expectedResult = service.addFavoriteRestaurantToCollectionIfMissing(favoriteRestaurantCollection, favoriteRestaurant);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(favoriteRestaurant);
      });

      it('should add only unique FavoriteRestaurant to an array', () => {
        const favoriteRestaurantArray: IFavoriteRestaurant[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const favoriteRestaurantCollection: IFavoriteRestaurant[] = [sampleWithRequiredData];
        expectedResult = service.addFavoriteRestaurantToCollectionIfMissing(favoriteRestaurantCollection, ...favoriteRestaurantArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const favoriteRestaurant: IFavoriteRestaurant = sampleWithRequiredData;
        const favoriteRestaurant2: IFavoriteRestaurant = sampleWithPartialData;
        expectedResult = service.addFavoriteRestaurantToCollectionIfMissing([], favoriteRestaurant, favoriteRestaurant2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(favoriteRestaurant);
        expect(expectedResult).toContain(favoriteRestaurant2);
      });

      it('should accept null and undefined values', () => {
        const favoriteRestaurant: IFavoriteRestaurant = sampleWithRequiredData;
        expectedResult = service.addFavoriteRestaurantToCollectionIfMissing([], null, favoriteRestaurant, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(favoriteRestaurant);
      });

      it('should return initial array if no FavoriteRestaurant is added', () => {
        const favoriteRestaurantCollection: IFavoriteRestaurant[] = [sampleWithRequiredData];
        expectedResult = service.addFavoriteRestaurantToCollectionIfMissing(favoriteRestaurantCollection, undefined, null);
        expect(expectedResult).toEqual(favoriteRestaurantCollection);
      });
    });

    describe('compareFavoriteRestaurant', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFavoriteRestaurant(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareFavoriteRestaurant(entity1, entity2);
        const compareResult2 = service.compareFavoriteRestaurant(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareFavoriteRestaurant(entity1, entity2);
        const compareResult2 = service.compareFavoriteRestaurant(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareFavoriteRestaurant(entity1, entity2);
        const compareResult2 = service.compareFavoriteRestaurant(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
