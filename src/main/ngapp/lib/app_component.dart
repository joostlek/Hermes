import 'package:angular/angular.dart';

@Component(
  selector: 'my-app',
  template: '<h1>Hello {{name}}</h1><app-layout></app-layout>',
)
class AppComponent {
  var name = 'Angular';
}
