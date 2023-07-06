import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IFoodIngredient} from '../food-ingredient.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../food-ingredient.test-samples';

import {FoodIngredientService} from './food-ingredient.service';

const requireRestSample: IFoodIngredient = {
  ...sampleWithRequiredData,
};

describe('FoodIngredient Service', () => {
  let service: FoodIngredientService;
  let httpMock: HttpTestingController;
  let expectedResult: IFoodIngredient | IFoodIngredient[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FoodIngredientService);
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

    it('should create a FoodIngredient', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const foodIngredient = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(foodIngredient).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FoodIngredient', () => {
      const foodIngredient = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(foodIngredient).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FoodIngredient', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FoodIngredient', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FoodIngredient', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addFoodIngredientToCollectionIfMissing', () => {
      it('should add a FoodIngredient to an empty array', () => {
        const foodIngredient: IFoodIngredient = sampleWithRequiredData;
        expectedResult = service.addFoodIngredientToCollectionIfMissing([], foodIngredient);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodIngredient);
      });

      it('should not add a FoodIngredient to an array that contains it', () => {
        const foodIngredient: IFoodIngredient = sampleWithRequiredData;
        const foodIngredientCollection: IFoodIngredient[] = [
          {
            ...foodIngredient,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFoodIngredientToCollectionIfMissing(foodIngredientCollection, foodIngredient);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FoodIngredient to an array that doesn't contain it", () => {
        const foodIngredient: IFoodIngredient = sampleWithRequiredData;
        const foodIngredientCollection: IFoodIngredient[] = [sampleWithPartialData];
        expectedResult = service.addFoodIngredientToCollectionIfMissing(foodIngredientCollection, foodIngredient);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodIngredient);
      });

      it('should add only unique FoodIngredient to an array', () => {
        const foodIngredientArray: IFoodIngredient[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const foodIngredientCollection: IFoodIngredient[] = [sampleWithRequiredData];
        expectedResult = service.addFoodIngredientToCollectionIfMissing(foodIngredientCollection, ...foodIngredientArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const foodIngredient: IFoodIngredient = sampleWithRequiredData;
        const foodIngredient2: IFoodIngredient = sampleWithPartialData;
        expectedResult = service.addFoodIngredientToCollectionIfMissing([], foodIngredient, foodIngredient2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodIngredient);
        expect(expectedResult).toContain(foodIngredient2);
      });

      it('should accept null and undefined values', () => {
        const foodIngredient: IFoodIngredient = sampleWithRequiredData;
        expectedResult = service.addFoodIngredientToCollectionIfMissing([], null, foodIngredient, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodIngredient);
      });

      it('should return initial array if no FoodIngredient is added', () => {
        const foodIngredientCollection: IFoodIngredient[] = [sampleWithRequiredData];
        expectedResult = service.addFoodIngredientToCollectionIfMissing(foodIngredientCollection, undefined, null);
        expect(expectedResult).toEqual(foodIngredientCollection);
      });
    });

    describe('compareFoodIngredient', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFoodIngredient(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareFoodIngredient(entity1, entity2);
        const compareResult2 = service.compareFoodIngredient(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareFoodIngredient(entity1, entity2);
        const compareResult2 = service.compareFoodIngredient(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareFoodIngredient(entity1, entity2);
        const compareResult2 = service.compareFoodIngredient(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
