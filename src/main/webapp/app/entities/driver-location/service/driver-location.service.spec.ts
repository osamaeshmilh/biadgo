import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IDriverLocation} from '../driver-location.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../driver-location.test-samples';

import {DriverLocationService, RestDriverLocation} from './driver-location.service';

const requireRestSample: RestDriverLocation = {
  ...sampleWithRequiredData,
  locationDateTime: sampleWithRequiredData.locationDateTime?.toJSON(),
};

describe('DriverLocation Service', () => {
  let service: DriverLocationService;
  let httpMock: HttpTestingController;
  let expectedResult: IDriverLocation | IDriverLocation[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DriverLocationService);
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

    it('should create a DriverLocation', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const driverLocation = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(driverLocation).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DriverLocation', () => {
      const driverLocation = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(driverLocation).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DriverLocation', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DriverLocation', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a DriverLocation', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addDriverLocationToCollectionIfMissing', () => {
      it('should add a DriverLocation to an empty array', () => {
        const driverLocation: IDriverLocation = sampleWithRequiredData;
        expectedResult = service.addDriverLocationToCollectionIfMissing([], driverLocation);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(driverLocation);
      });

      it('should not add a DriverLocation to an array that contains it', () => {
        const driverLocation: IDriverLocation = sampleWithRequiredData;
        const driverLocationCollection: IDriverLocation[] = [
          {
            ...driverLocation,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDriverLocationToCollectionIfMissing(driverLocationCollection, driverLocation);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DriverLocation to an array that doesn't contain it", () => {
        const driverLocation: IDriverLocation = sampleWithRequiredData;
        const driverLocationCollection: IDriverLocation[] = [sampleWithPartialData];
        expectedResult = service.addDriverLocationToCollectionIfMissing(driverLocationCollection, driverLocation);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(driverLocation);
      });

      it('should add only unique DriverLocation to an array', () => {
        const driverLocationArray: IDriverLocation[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const driverLocationCollection: IDriverLocation[] = [sampleWithRequiredData];
        expectedResult = service.addDriverLocationToCollectionIfMissing(driverLocationCollection, ...driverLocationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const driverLocation: IDriverLocation = sampleWithRequiredData;
        const driverLocation2: IDriverLocation = sampleWithPartialData;
        expectedResult = service.addDriverLocationToCollectionIfMissing([], driverLocation, driverLocation2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(driverLocation);
        expect(expectedResult).toContain(driverLocation2);
      });

      it('should accept null and undefined values', () => {
        const driverLocation: IDriverLocation = sampleWithRequiredData;
        expectedResult = service.addDriverLocationToCollectionIfMissing([], null, driverLocation, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(driverLocation);
      });

      it('should return initial array if no DriverLocation is added', () => {
        const driverLocationCollection: IDriverLocation[] = [sampleWithRequiredData];
        expectedResult = service.addDriverLocationToCollectionIfMissing(driverLocationCollection, undefined, null);
        expect(expectedResult).toEqual(driverLocationCollection);
      });
    });

    describe('compareDriverLocation', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDriverLocation(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareDriverLocation(entity1, entity2);
        const compareResult2 = service.compareDriverLocation(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareDriverLocation(entity1, entity2);
        const compareResult2 = service.compareDriverLocation(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareDriverLocation(entity1, entity2);
        const compareResult2 = service.compareDriverLocation(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
