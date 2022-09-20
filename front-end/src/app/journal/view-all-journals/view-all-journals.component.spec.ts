import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllJournalsComponent } from './view-all-journals.component';

describe('ViewAllJournalsComponent', () => {
  let component: ViewAllJournalsComponent;
  let fixture: ComponentFixture<ViewAllJournalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAllJournalsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAllJournalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
