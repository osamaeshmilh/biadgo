import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IDriverReview} from '../driver-review.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../driver-review.test-samples';

import {DriverReviewService} from './driver-review.service';

const requireRestSample: IDriverReview = {
  ...sampleWithRequiredData,
};

describe('DriverReview Service', () => {
  let service: DriverReviewService;
  let httpMock: HttpTestingController;
  let expectedResult: IDriverReview | IDriverReview[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DriverReviewService);
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

    it('should create a DriverReview', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const driverReview = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(driverReview).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DriverReview', () => {
      const driverReview = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(driverReview).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DriverReview', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DriverReview', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DriverReview', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addDriverReviewToCollectionIfMissing', () => {
      it('should add a DriverReview to an empty array', () => {
        const driverReview: IDriverReview = sampleWithRequiredData;
        expectedResult = service.addDriverReviewToCollectionIfMissing([], driverReview);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(driverReview);
      });

      it('should not add a DriverReview to an array that contains it', () => {
        const driverReview: IDriverReview = sampleWithRequiredData;
        const driverReviewCollection: IDriverReview[] = [
          {
            ...driverReview,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDriverReviewToCollectionIfMissing(driverReviewCollection, driverReview);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DriverReview to an array that doesn't contain it", () => {
        const driverReview: IDriverReview = sampleWithRequiredData;
        const driverReviewCollection: IDriverReview[] = [sampleWithPartialData];
        expectedResult = service.addDriverReviewToCollectionIfMissing(driverReviewCollection, driverReview);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(driverReview);
      });

      it('should add only unique DriverReview to an array', () => {
        const driverReviewArray: IDriverReview[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const driverReviewCollection: IDriverReview[] = [sampleWithRequiredData];
        expectedResult = service.addDriverReviewToCollectionIfMissing(driverReviewCollection, ...driverReviewArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const driverReview: IDriverReview = sampleWithRequiredData;
        const driverReview2: IDriverReview = sampleWithPartialData;
        expectedResult = service.addDriverReviewToCollectionIfMissing([], driverReview, driverReview2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(driverReview);
        expect(expectedResult).toContain(driverReview2);
      });

      it('should accept null and undefined values', () => {
        const driverReview: IDriverReview = sampleWithRequiredData;
        expectedResult = service.addDriverReviewToCollectionIfMissing([], null, driverReview, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(driverReview);
      });

      it('should return initial array if no DriverReview is added', () => {
        const driverReviewCollection: IDriverReview[] = [sampleWithRequiredData];
        expectedResult = service.addDriverReviewToCollectionIfMissing(driverReviewCollection, undefined, null);
        expect(expectedResult).toEqual(driverReviewCollection);
      });
    });

    describe('compareDriverReview', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDriverReview(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareDriverReview(entity1, entity2);
        const compareResult2 = service.compareDriverReview(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareDriverReview(entity1, entity2);
        const compareResult2 = service.compareDriverReview(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareDriverReview(entity1, entity2);
        const compareResult2 = service.compareDriverReview(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
