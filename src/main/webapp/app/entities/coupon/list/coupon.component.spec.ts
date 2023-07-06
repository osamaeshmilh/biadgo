import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpHeaders, HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {CouponService} from '../service/coupon.service';

import {CouponComponent} from './coupon.component';
import SpyInstance = jest.SpyInstance;

describe('Coupon Management Component', () => {
  let comp: CouponComponent;
  let fixture: ComponentFixture<CouponComponent>;
  let service: CouponService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{
        path: 'coupon',
        component: CouponComponent
      }]), HttpClientTestingModule, CouponComponent],
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
      .overrideTemplate(CouponComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CouponComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(CouponService);
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
    expect(comp.coupons?.[0]).toEqual(expect.objectContaining({id: 123}));
  });

  describe('trackId', () => {
    it('Should forward to couponService', () => {
      const entity = {id: 123};
      jest.spyOn(service, 'getCouponIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getCouponIdentifier).toHaveBeenCalledWith(entity);
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
