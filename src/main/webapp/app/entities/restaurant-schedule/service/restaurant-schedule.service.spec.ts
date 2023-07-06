import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IRestaurantSchedule} from '../restaurant-schedule.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../restaurant-schedule.test-samples';

import {RestaurantScheduleService} from './restaurant-schedule.service';

const requireRestSample: IRestaurantSchedule = {
  ...sampleWithRequiredData,
};

describe('RestaurantSchedule Service', () => {
  let service: RestaurantScheduleService;
  let httpMock: HttpTestingController;
  let expectedResult: IRestaurantSchedule | IRestaurantSchedule[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RestaurantScheduleService);
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

    it('should create a RestaurantSchedule', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const restaurantSchedule = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(restaurantSchedule).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RestaurantSchedule', () => {
      const restaurantSchedule = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(restaurantSchedule).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RestaurantSchedule', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RestaurantSchedule', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RestaurantSchedule', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addRestaurantScheduleToCollectionIfMissing', () => {
      it('should add a RestaurantSchedule to an empty array', () => {
        const restaurantSchedule: IRestaurantSchedule = sampleWithRequiredData;
        expectedResult = service.addRestaurantScheduleToCollectionIfMissing([], restaurantSchedule);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantSchedule);
      });

      it('should not add a RestaurantSchedule to an array that contains it', () => {
        const restaurantSchedule: IRestaurantSchedule = sampleWithRequiredData;
        const restaurantScheduleCollection: IRestaurantSchedule[] = [
          {
            ...restaurantSchedule,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRestaurantScheduleToCollectionIfMissing(restaurantScheduleCollection, restaurantSchedule);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RestaurantSchedule to an array that doesn't contain it", () => {
        const restaurantSchedule: IRestaurantSchedule = sampleWithRequiredData;
        const restaurantScheduleCollection: IRestaurantSchedule[] = [sampleWithPartialData];
        expectedResult = service.addRestaurantScheduleToCollectionIfMissing(restaurantScheduleCollection, restaurantSchedule);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantSchedule);
      });

      it('should add only unique RestaurantSchedule to an array', () => {
        const restaurantScheduleArray: IRestaurantSchedule[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const restaurantScheduleCollection: IRestaurantSchedule[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantScheduleToCollectionIfMissing(restaurantScheduleCollection, ...restaurantScheduleArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const restaurantSchedule: IRestaurantSchedule = sampleWithRequiredData;
        const restaurantSchedule2: IRestaurantSchedule = sampleWithPartialData;
        expectedResult = service.addRestaurantScheduleToCollectionIfMissing([], restaurantSchedule, restaurantSchedule2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantSchedule);
        expect(expectedResult).toContain(restaurantSchedule2);
      });

      it('should accept null and undefined values', () => {
        const restaurantSchedule: IRestaurantSchedule = sampleWithRequiredData;
        expectedResult = service.addRestaurantScheduleToCollectionIfMissing([], null, restaurantSchedule, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantSchedule);
      });

      it('should return initial array if no RestaurantSchedule is added', () => {
        const restaurantScheduleCollection: IRestaurantSchedule[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantScheduleToCollectionIfMissing(restaurantScheduleCollection, undefined, null);
        expect(expectedResult).toEqual(restaurantScheduleCollection);
      });
    });

    describe('compareRestaurantSchedule', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRestaurantSchedule(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareRestaurantSchedule(entity1, entity2);
        const compareResult2 = service.compareRestaurantSchedule(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareRestaurantSchedule(entity1, entity2);
        const compareResult2 = service.compareRestaurantSchedule(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareRestaurantSchedule(entity1, entity2);
        const compareResult2 = service.compareRestaurantSchedule(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
