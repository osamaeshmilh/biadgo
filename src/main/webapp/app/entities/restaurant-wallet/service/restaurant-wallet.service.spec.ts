import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {IRestaurantWallet} from '../restaurant-wallet.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../restaurant-wallet.test-samples';

import {RestaurantWalletService} from './restaurant-wallet.service';

const requireRestSample: IRestaurantWallet = {
  ...sampleWithRequiredData,
};

describe('RestaurantWallet Service', () => {
  let service: RestaurantWalletService;
  let httpMock: HttpTestingController;
  let expectedResult: IRestaurantWallet | IRestaurantWallet[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RestaurantWalletService);
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

    it('should create a RestaurantWallet', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const restaurantWallet = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(restaurantWallet).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RestaurantWallet', () => {
      const restaurantWallet = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(restaurantWallet).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RestaurantWallet', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RestaurantWallet', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RestaurantWallet', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addRestaurantWalletToCollectionIfMissing', () => {
      it('should add a RestaurantWallet to an empty array', () => {
        const restaurantWallet: IRestaurantWallet = sampleWithRequiredData;
        expectedResult = service.addRestaurantWalletToCollectionIfMissing([], restaurantWallet);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantWallet);
      });

      it('should not add a RestaurantWallet to an array that contains it', () => {
        const restaurantWallet: IRestaurantWallet = sampleWithRequiredData;
        const restaurantWalletCollection: IRestaurantWallet[] = [
          {
            ...restaurantWallet,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRestaurantWalletToCollectionIfMissing(restaurantWalletCollection, restaurantWallet);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RestaurantWallet to an array that doesn't contain it", () => {
        const restaurantWallet: IRestaurantWallet = sampleWithRequiredData;
        const restaurantWalletCollection: IRestaurantWallet[] = [sampleWithPartialData];
        expectedResult = service.addRestaurantWalletToCollectionIfMissing(restaurantWalletCollection, restaurantWallet);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantWallet);
      });

      it('should add only unique RestaurantWallet to an array', () => {
        const restaurantWalletArray: IRestaurantWallet[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const restaurantWalletCollection: IRestaurantWallet[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantWalletToCollectionIfMissing(restaurantWalletCollection, ...restaurantWalletArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const restaurantWallet: IRestaurantWallet = sampleWithRequiredData;
        const restaurantWallet2: IRestaurantWallet = sampleWithPartialData;
        expectedResult = service.addRestaurantWalletToCollectionIfMissing([], restaurantWallet, restaurantWallet2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(restaurantWallet);
        expect(expectedResult).toContain(restaurantWallet2);
      });

      it('should accept null and undefined values', () => {
        const restaurantWallet: IRestaurantWallet = sampleWithRequiredData;
        expectedResult = service.addRestaurantWalletToCollectionIfMissing([], null, restaurantWallet, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(restaurantWallet);
      });

      it('should return initial array if no RestaurantWallet is added', () => {
        const restaurantWalletCollection: IRestaurantWallet[] = [sampleWithRequiredData];
        expectedResult = service.addRestaurantWalletToCollectionIfMissing(restaurantWalletCollection, undefined, null);
        expect(expectedResult).toEqual(restaurantWalletCollection);
      });
    });

    describe('compareRestaurantWallet', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRestaurantWallet(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareRestaurantWallet(entity1, entity2);
        const compareResult2 = service.compareRestaurantWallet(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareRestaurantWallet(entity1, entity2);
        const compareResult2 = service.compareRestaurantWallet(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareRestaurantWallet(entity1, entity2);
        const compareResult2 = service.compareRestaurantWallet(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
