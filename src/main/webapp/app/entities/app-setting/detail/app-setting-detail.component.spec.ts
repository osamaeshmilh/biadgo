import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {AppSettingDetailComponent} from './app-setting-detail.component';

describe('AppSetting Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppSettingDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AppSettingDetailComponent,
              resolve: {appSetting: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(AppSettingDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load appSetting on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AppSettingDetailComponent);

      // THEN
      expect(instance.appSetting).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
