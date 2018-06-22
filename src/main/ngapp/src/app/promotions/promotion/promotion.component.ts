import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material";
import {DeleteAlertComponent} from "@app/_dialogs/delete-alert/delete-alert.component";
import {Promotion} from "@app/_models/promotion";
import {PromotionService} from "@app/_services/promotion.service";

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.scss']
})
export class PromotionComponent implements OnInit {
  promotion: Promotion;
  formGroup: FormGroup;
  edit: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private promotionService: PromotionService,
    private location: Location,
    private _FormBuilder: FormBuilder,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getPromotion();
  }

  getPromotion() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.promotionService.getPromotion(id)
      .subscribe(promotion => {
        this.promotion = promotion;
        this.formGroup = this._FormBuilder.group({
          name: [promotion.name, Validators.required],
          type: [{value: promotion.type['name'], disabled: true}],
          user: [{value: promotion.owner['firstName'], disabled: true}],
          startDate: [{value: promotion.startDate, disabled: true}]
        })
      });
  }

  goBack(): void {
    this.location.back();
  }

  askDelete(): void {
    let dialogRef = this.dialog.open(DeleteAlertComponent, {
      width: '300px',
      data: {delete: this.deleteType, name: this.promotion.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.deleteType();
      }
    })
  }

  deleteType(): void {
    this.promotionService.deletePromotion(this.promotion);
    this.location.back();
  }

  editType(): void {
    this.edit = true;
  }

  finishEdit(): void {
    this.edit = false;
    this.promotion.name = this.formGroup.get('name').value;
    this.promotionService.updatePromotion(this.promotion)
      .subscribe(result => console.log(result));
  }

}
