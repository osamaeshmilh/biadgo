import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpHeaders, HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {FoodImageService} from '../service/food-image.service';

import {FoodImageComponent} from './food-image.component';
import SpyInstance = jest.SpyInstance;

describe('FoodImage Management Component', () => {
  let comp: FoodImageComponent;
  let fixture: ComponentFixture<FoodImageComponent>;
  let service: FoodImageService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{path: 'food-image', component: FoodImageComponent}]),
        HttpClientTestingModule,
        FoodImageComponent,
      ],
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
      .overrideTemplate(FoodImageComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FoodImageComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FoodImageService);
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
    expect(comp.foodImages?.[0]).toEqual(expect.objectContaining({id: 123}));
  });

  describe('trackId', () => {
    it('Should forward to foodImageService', () => {
      const entity = {id: 123};
      jest.spyOn(service, 'getFoodImageIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getFoodImageIdentifier).toHaveBeenCalledWith(entity);
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
