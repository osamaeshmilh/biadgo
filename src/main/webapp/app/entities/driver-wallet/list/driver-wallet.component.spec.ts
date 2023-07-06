import {ComponentFixture, TestBed} from '@angular/core/testing';
import {HttpHeaders, HttpResponse} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {DriverWalletService} from '../service/driver-wallet.service';

import {DriverWalletComponent} from './driver-wallet.component';
import SpyInstance = jest.SpyInstance;

describe('DriverWallet Management Component', () => {
  let comp: DriverWalletComponent;
  let fixture: ComponentFixture<DriverWalletComponent>;
  let service: DriverWalletService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{path: 'driver-wallet', component: DriverWalletComponent}]),
        HttpClientTestingModule,
        DriverWalletComponent,
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
      .overrideTemplate(DriverWalletComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DriverWalletComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DriverWalletService);
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
    expect(comp.driverWallets?.[0]).toEqual(expect.objectContaining({id: 123}));
  });

  describe('trackId', () => {
    it('Should forward to driverWalletService', () => {
      const entity = {id: 123};
      jest.spyOn(service, 'getDriverWalletIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getDriverWalletIdentifier).toHaveBeenCalledWith(entity);
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
