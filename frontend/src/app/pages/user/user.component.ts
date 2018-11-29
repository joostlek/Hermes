import {Component, OnInit} from '@angular/core';
import {User} from '../../@core/data/domain/user';
import {Image} from '../../@core/data/domain/image';
import {Location} from '../../@core/data/domain/location';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../../@core/data/user.service';
import {ImageService} from '../../@core/data/image.service';
import {LocationService} from '../../@core/data/location.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
    user: User;
    images: Image[] = [];
    locations: Location[] = [];
    informationActive = true;
    imagesActive = false;
    locationsActive = false;

    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private imageService: ImageService,
        private locationService: LocationService,
    ) {
    }

    ngOnInit() {
        this.getParameter();
    }

    getParameter(): void {
        this.route.params.subscribe(
            (params) => {
                this.getUser(+params['id']);
            },
        );
    }

    getUser(id: number): void {
        this.userService.getUserById(id)
            .subscribe((user) => {
                this.user = user;
                this.getImages();
                this.getLocations();
            });
    }

    getImages(): void {
        this.imageService.getImagesByUser(this.user)
            .subscribe((images) => this.images = images);
    }

    getLocations(): void {
        this.locationService.getLocationsByUser(this.user)
            .subscribe((locations) => this.locations = locations);
    }


}
