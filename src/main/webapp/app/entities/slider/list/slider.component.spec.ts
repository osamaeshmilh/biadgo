import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpHeaders, HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {SliderService} from '../service/slider.service';

import {SliderComponent} from './slider.component';
import SpyInstance = jest.SpyInstance;

describe('Slider Management Component', () => {
  let comp: SliderComponent;
  let fixture: ComponentFixture<SliderComponent>;
  let service: SliderService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{
        path: 'slider',
        component: SliderComponent
      }]), HttpClientTestingModule, SliderComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
                'filter[someId.in]': 'dc4279ea-cfb9-11ec-9d64-0242ac120002',
              })
            ),
            snapshot: {queryParams: {}},
          },
        },
      ],
    })
      .overrideTemplate(SliderComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SliderComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(SliderService);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{id: 123}],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.sliders?.[0]).toEqual(expect.objectContaining({id: 123}));
  });

  describe('trackId', () => {
    it('Should forward to sliderService', () => {
      const entity = {id: 123};
      jest.spyOn(service, 'getSliderIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getSliderIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });

  it('should load a page', () => {
    // WHEN
    comp.navigateToPage(1);

    // THEN
    expect(routerNavigateSpy).toHaveBeenCalled();
  });

  it('should calculate the sort attribute for an id', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({sort: ['id,desc']}));
  });

  it('should calculate the sort attribute for a non-id attribute', () => {
    // GIVEN
    comp.predicate = 'name';

    // WHEN
    comp.navigateToWithComponentValues();

    // THEN
    expect(routerNavigateSpy).toHaveBeenLastCalledWith(
      expect.anything(),
      expect.objectContaining({
        queryParams: expect.objectContaining({
          sort: ['name,asc'],
        }),
      })
    );
  });

  it('should calculate the filter attribute', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({'someId.in': ['dc4279ea-cfb9-11ec-9d64-0242ac120002']}));
  });
});
