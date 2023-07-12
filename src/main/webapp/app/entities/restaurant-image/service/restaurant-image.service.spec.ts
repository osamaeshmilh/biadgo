import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IRestaurantImage} from '../restaurant-image.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../restaurant-image.test-samples';

import {RestaurantImageService} from './restaurant-image.service';

const requireRestSample: IRestaurantImage = {
  ...sampleWithRequiredData,
};

describe('RestaurantImage Service', () => {
  let service: RestaurantImageService;
  let httpMock: HttpTestingController;
  let expectedResult: IRestaurantImage | IRestaurantImage[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RestaurantImageService);
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

    it('should create a RestaurantImage', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const restaurantImage = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(restaurantImage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RestaurantImage', () => {
      const restaurantImage = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(restaurantImage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RestaurantImage', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RestaurantImage', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RestaurantImage', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addRestaurantImageToCollectionIfMissing', () => {
      it('should add a RestaurantImage to an empty array', () => {
        const restaurantImage: IRestaurantImage = sampleWithRequiredData;
        expectedResult = service.addRestaurantImageToCollectionIfMissing([], restaurantImage);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantImage);
      });

      it('should not add a RestaurantImage to an array that contains it', () => {
        const restaurantImage: IRestaurantImage = sampleWithRequiredData;
        const restaurantImageCollection: IRestaurantImage[] = [
          {
            ...restaurantImage,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRestaurantImageToCollectionIfMissing(restaurantImageCollection, restaurantImage);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RestaurantImage to an array that doesn't contain it", () => {
        const restaurantImage: IRestaurantImage = sampleWithRequiredData;
        const restaurantImageCollection: IRestaurantImage[] = [sampleWithPartialData];
        expectedResult = service.addRestaurantImageToCollectionIfMissing(restaurantImageCollection, restaurantImage);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantImage);
      });

      it('should add only unique RestaurantImage to an array', () => {
        const restaurantImageArray: IRestaurantImage[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const restaurantImageCollection: IRestaurantImage[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantImageToCollectionIfMissing(restaurantImageCollection, ...restaurantImageArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const restaurantImage: IRestaurantImage = sampleWithRequiredData;
        const restaurantImage2: IRestaurantImage = sampleWithPartialData;
        expectedResult = service.addRestaurantImageToCollectionIfMissing([], restaurantImage, restaurantImage2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantImage);
        expect(expectedResult).toContain(restaurantImage2);
      });

      it('should accept null and undefined values', () => {
        const restaurantImage: IRestaurantImage = sampleWithRequiredData;
        expectedResult = service.addRestaurantImageToCollectionIfMissing([], null, restaurantImage, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantImage);
      });

      it('should return initial array if no RestaurantImage is added', () => {
        const restaurantImageCollection: IRestaurantImage[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantImageToCollectionIfMissing(restaurantImageCollection, undefined, null);
        expect(expectedResult).toEqual(restaurantImageCollection);
      });
    });

    describe('compareRestaurantImage', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRestaurantImage(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareRestaurantImage(entity1, entity2);
        const compareResult2 = service.compareRestaurantImage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareRestaurantImage(entity1, entity2);
        const compareResult2 = service.compareRestaurantImage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareRestaurantImage(entity1, entity2);
        const compareResult2 = service.compareRestaurantImage(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
