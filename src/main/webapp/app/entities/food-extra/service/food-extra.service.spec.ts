import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IFoodExtra} from '../food-extra.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../food-extra.test-samples';

import {FoodExtraService} from './food-extra.service';

const requireRestSample: IFoodExtra = {
  ...sampleWithRequiredData,
};

describe('FoodExtra Service', () => {
  let service: FoodExtraService;
  let httpMock: HttpTestingController;
  let expectedResult: IFoodExtra | IFoodExtra[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FoodExtraService);
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

    it('should create a FoodExtra', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const foodExtra = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(foodExtra).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FoodExtra', () => {
      const foodExtra = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(foodExtra).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FoodExtra', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FoodExtra', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FoodExtra', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addFoodExtraToCollectionIfMissing', () => {
      it('should add a FoodExtra to an empty array', () => {
        const foodExtra: IFoodExtra = sampleWithRequiredData;
        expectedResult = service.addFoodExtraToCollectionIfMissing([], foodExtra);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodExtra);
      });

      it('should not add a FoodExtra to an array that contains it', () => {
        const foodExtra: IFoodExtra = sampleWithRequiredData;
        const foodExtraCollection: IFoodExtra[] = [
          {
            ...foodExtra,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFoodExtraToCollectionIfMissing(foodExtraCollection, foodExtra);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FoodExtra to an array that doesn't contain it", () => {
        const foodExtra: IFoodExtra = sampleWithRequiredData;
        const foodExtraCollection: IFoodExtra[] = [sampleWithPartialData];
        expectedResult = service.addFoodExtraToCollectionIfMissing(foodExtraCollection, foodExtra);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodExtra);
      });

      it('should add only unique FoodExtra to an array', () => {
        const foodExtraArray: IFoodExtra[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const foodExtraCollection: IFoodExtra[] = [sampleWithRequiredData];
        expectedResult = service.addFoodExtraToCollectionIfMissing(foodExtraCollection, ...foodExtraArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const foodExtra: IFoodExtra = sampleWithRequiredData;
        const foodExtra2: IFoodExtra = sampleWithPartialData;
        expectedResult = service.addFoodExtraToCollectionIfMissing([], foodExtra, foodExtra2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodExtra);
        expect(expectedResult).toContain(foodExtra2);
      });

      it('should accept null and undefined values', () => {
        const foodExtra: IFoodExtra = sampleWithRequiredData;
        expectedResult = service.addFoodExtraToCollectionIfMissing([], null, foodExtra, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodExtra);
      });

      it('should return initial array if no FoodExtra is added', () => {
        const foodExtraCollection: IFoodExtra[] = [sampleWithRequiredData];
        expectedResult = service.addFoodExtraToCollectionIfMissing(foodExtraCollection, undefined, null);
        expect(expectedResult).toEqual(foodExtraCollection);
      });
    });

    describe('compareFoodExtra', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFoodExtra(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareFoodExtra(entity1, entity2);
        const compareResult2 = service.compareFoodExtra(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareFoodExtra(entity1, entity2);
        const compareResult2 = service.compareFoodExtra(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareFoodExtra(entity1, entity2);
        const compareResult2 = service.compareFoodExtra(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
