import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IFoodImage} from '../food-image.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../food-image.test-samples';

import {FoodImageService} from './food-image.service';

const requireRestSample: IFoodImage = {
  ...sampleWithRequiredData,
};

describe('FoodImage Service', () => {
  let service: FoodImageService;
  let httpMock: HttpTestingController;
  let expectedResult: IFoodImage | IFoodImage[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FoodImageService);
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

    it('should create a FoodImage', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const foodImage = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(foodImage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FoodImage', () => {
      const foodImage = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(foodImage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FoodImage', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FoodImage', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FoodImage', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addFoodImageToCollectionIfMissing', () => {
      it('should add a FoodImage to an empty array', () => {
        const foodImage: IFoodImage = sampleWithRequiredData;
        expectedResult = service.addFoodImageToCollectionIfMissing([], foodImage);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodImage);
      });

      it('should not add a FoodImage to an array that contains it', () => {
        const foodImage: IFoodImage = sampleWithRequiredData;
        const foodImageCollection: IFoodImage[] = [
          {
            ...foodImage,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFoodImageToCollectionIfMissing(foodImageCollection, foodImage);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FoodImage to an array that doesn't contain it", () => {
        const foodImage: IFoodImage = sampleWithRequiredData;
        const foodImageCollection: IFoodImage[] = [sampleWithPartialData];
        expectedResult = service.addFoodImageToCollectionIfMissing(foodImageCollection, foodImage);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodImage);
      });

      it('should add only unique FoodImage to an array', () => {
        const foodImageArray: IFoodImage[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const foodImageCollection: IFoodImage[] = [sampleWithRequiredData];
        expectedResult = service.addFoodImageToCollectionIfMissing(foodImageCollection, ...foodImageArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const foodImage: IFoodImage = sampleWithRequiredData;
        const foodImage2: IFoodImage = sampleWithPartialData;
        expectedResult = service.addFoodImageToCollectionIfMissing([], foodImage, foodImage2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foodImage);
        expect(expectedResult).toContain(foodImage2);
      });

      it('should accept null and undefined values', () => {
        const foodImage: IFoodImage = sampleWithRequiredData;
        expectedResult = service.addFoodImageToCollectionIfMissing([], null, foodImage, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foodImage);
      });

      it('should return initial array if no FoodImage is added', () => {
        const foodImageCollection: IFoodImage[] = [sampleWithRequiredData];
        expectedResult = service.addFoodImageToCollectionIfMissing(foodImageCollection, undefined, null);
        expect(expectedResult).toEqual(foodImageCollection);
      });
    });

    describe('compareFoodImage', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFoodImage(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareFoodImage(entity1, entity2);
        const compareResult2 = service.compareFoodImage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareFoodImage(entity1, entity2);
        const compareResult2 = service.compareFoodImage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareFoodImage(entity1, entity2);
        const compareResult2 = service.compareFoodImage(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
