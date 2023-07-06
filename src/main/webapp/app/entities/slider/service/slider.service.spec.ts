import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {ISlider} from '../slider.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData
} from '../slider.test-samples';

import {SliderService} from './slider.service';

const requireRestSample: ISlider = {
  ...sampleWithRequiredData,
};

describe('Slider Service', () => {
  let service: SliderService;
  let httpMock: HttpTestingController;
  let expectedResult: ISlider | ISlider[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SliderService);
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

    it('should create a Slider', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const slider = {...sampleWithNewData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.create(slider).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'POST'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Slider', () => {
      const slider = {...sampleWithRequiredData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.update(slider).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PUT'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Slider', () => {
      const patchObject = {...sampleWithPartialData};
      const returnedFromService = {...requireRestSample};
      const expected = {...sampleWithRequiredData};

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'PATCH'});
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Slider', () => {
      const returnedFromService = {...requireRestSample};

      const expected = {...sampleWithRequiredData};

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({method: 'GET'});
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Slider', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({method: 'DELETE'});
      req.flush({status: 200});
      expect(expectedResult).toBe(expected);
    });

    describe('addSliderToCollectionIfMissing', () => {
      it('should add a Slider to an empty array', () => {
        const slider: ISlider = sampleWithRequiredData;
        expectedResult = service.addSliderToCollectionIfMissing([], slider);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(slider);
      });

      it('should not add a Slider to an array that contains it', () => {
        const slider: ISlider = sampleWithRequiredData;
        const sliderCollection: ISlider[] = [
          {
            ...slider,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addSliderToCollectionIfMissing(sliderCollection, slider);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Slider to an array that doesn't contain it", () => {
        const slider: ISlider = sampleWithRequiredData;
        const sliderCollection: ISlider[] = [sampleWithPartialData];
        expectedResult = service.addSliderToCollectionIfMissing(sliderCollection, slider);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(slider);
      });

      it('should add only unique Slider to an array', () => {
        const sliderArray: ISlider[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const sliderCollection: ISlider[] = [sampleWithRequiredData];
        expectedResult = service.addSliderToCollectionIfMissing(sliderCollection, ...sliderArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const slider: ISlider = sampleWithRequiredData;
        const slider2: ISlider = sampleWithPartialData;
        expectedResult = service.addSliderToCollectionIfMissing([], slider, slider2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(slider);
        expect(expectedResult).toContain(slider2);
      });

      it('should accept null and undefined values', () => {
        const slider: ISlider = sampleWithRequiredData;
        expectedResult = service.addSliderToCollectionIfMissing([], null, slider, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(slider);
      });

      it('should return initial array if no Slider is added', () => {
        const sliderCollection: ISlider[] = [sampleWithRequiredData];
        expectedResult = service.addSliderToCollectionIfMissing(sliderCollection, undefined, null);
        expect(expectedResult).toEqual(sliderCollection);
      });
    });

    describe('compareSlider', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareSlider(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = {id: 123};
        const entity2 = null;

        const compareResult1 = service.compareSlider(entity1, entity2);
        const compareResult2 = service.compareSlider(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 456};

        const compareResult1 = service.compareSlider(entity1, entity2);
        const compareResult2 = service.compareSlider(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = {id: 123};
        const entity2 = {id: 123};

        const compareResult1 = service.compareSlider(entity1, entity2);
        const compareResult2 = service.compareSlider(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
