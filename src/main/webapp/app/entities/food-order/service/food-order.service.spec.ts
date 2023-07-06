import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IFoodOrder} from '../food-order.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../food-order.test-samples';

import {FoodOrderService} from './food-order.service';

const requireRestSample: IFoodOrder = {
  ...sampleWithRequiredData,
};

describe('FoodOrder Service', () => {
  let service: FoodOrderService;
  let httpMock: HttpTestingController;
  let expectedResult: IFoodOrder | IFoodOrder[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FoodOrderService);
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

    it('should create a FoodOrder', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const foodOrder = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(foodOrder).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FoodOrder', () => {
      const foodOrder = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(foodOrder).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FoodOrder', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FoodOrder', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FoodOrder', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addFoodOrderToCollectionIfMissing', () => {
      it('should add a FoodOrder to an empty array', () => {
        const foodOrder: IFoodOrder = sampleWithRequiredData;
        expectedResult = service.addFoodOrderToCollectionIfMissing([], foodOrder);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodOrder);
      });

      it('should not add a FoodOrder to an array that contains it', () => {
        const foodOrder: IFoodOrder = sampleWithRequiredData;
        const foodOrderCollection: IFoodOrder[] = [
          {
            ...foodOrder,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFoodOrderToCollectionIfMissing(foodOrderCollection, foodOrder);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FoodOrder to an array that doesn't contain it", () => {
        const foodOrder: IFoodOrder = sampleWithRequiredData;
        const foodOrderCollection: IFoodOrder[] = [sampleWithPartialData];
        expectedResult = service.addFoodOrderToCollectionIfMissing(foodOrderCollection, foodOrder);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodOrder);
      });

      it('should add only unique FoodOrder to an array', () => {
        const foodOrderArray: IFoodOrder[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const foodOrderCollection: IFoodOrder[] = [sampleWithRequiredData];
        expectedResult = service.addFoodOrderToCollectionIfMissing(foodOrderCollection, ...foodOrderArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const foodOrder: IFoodOrder = sampleWithRequiredData;
        const foodOrder2: IFoodOrder = sampleWithPartialData;
        expectedResult = service.addFoodOrderToCollectionIfMissing([], foodOrder, foodOrder2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodOrder);
        expect(expectedResult).toContain(foodOrder2);
      });

      it('should accept null and undefined values', () => {
        const foodOrder: IFoodOrder = sampleWithRequiredData;
        expectedResult = service.addFoodOrderToCollectionIfMissing([], null, foodOrder, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodOrder);
      });

      it('should return initial array if no FoodOrder is added', () => {
        const foodOrderCollection: IFoodOrder[] = [sampleWithRequiredData];
        expectedResult = service.addFoodOrderToCollectionIfMissing(foodOrderCollection, undefined, null);
        expect(expectedResult).toEqual(foodOrderCollection);
      });
    });

    describe('compareFoodOrder', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFoodOrder(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareFoodOrder(entity1, entity2);
        const compareResult2 = service.compareFoodOrder(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareFoodOrder(entity1, entity2);
        const compareResult2 = service.compareFoodOrder(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareFoodOrder(entity1, entity2);
        const compareResult2 = service.compareFoodOrder(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
