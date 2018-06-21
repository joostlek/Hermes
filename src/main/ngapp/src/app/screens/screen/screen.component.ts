import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {Screen} from "@app/_models/screen";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material";
import {DeleteAlertComponent} from "@app/_dialogs/delete-alert/delete-alert.component";
import {ScreenService} from "@app/_services/screen.service";

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.scss']
})
export class ScreenComponent implements OnInit {
  screen: Screen;
  formGroup: FormGroup;
  edit: boolean = false;
  constructor(private route: ActivatedRoute,
              private screenService: ScreenService,
              private location: Location,
              private _FormBuilder: FormBuilder,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.getScreen();
    this.formGroup = this._FormBuilder.group({
      name: [Validators.required],
      height: [Validators.required],
      width: [Validators.required],
    })
  }

  getScreen() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.screenService.getScreen(id)
      .subscribe(screen => this.screen = screen);
  }

  goBack(): void {
    this.location.back();
  }

  askDelete(): void {
    let dialogRef = this.dialog.open(DeleteAlertComponent, {
      width: '300px',
      data: {delete: this.deleteType, name: this.screen.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.deleteType();
      }
    })
  }

  deleteType(): void {
    this.screenService.deleteScreen(this.screen);
    this.location.back();
  }

  editType(): void {
    this.edit = true;
  }

  finishEdit(): void {
    this.edit = false;
    this.screen.name = this.formGroup.value['name'];
    this.screen.height = this.formGroup.value['height'];
    this.screen.width = this.formGroup.value['width'];
    this.screenService.editScreen(this.screen)
      .subscribe(_ => console.log(_));
  }

}
