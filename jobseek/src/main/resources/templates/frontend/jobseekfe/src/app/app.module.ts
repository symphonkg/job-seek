import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterSuccessComponent } from './auth/register-success/register-success.component'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    LoginComponent,
    RegisterSuccessComponent  
  ],
  imports: [
    BrowserModule, // BrowserModule è il componente base per lavorare con i template HTML nel browser. Grazie a questo modulo è possibile compilare direttive come NgIf e NgFor (anche se in realtà fanno parte del modulo CommonModule che è importato e riesportato come dipendenza)
    AppRoutingModule, // permette di passare da una directory add un altra, da un path all'altro
    FormsModule, // abilita ngModel e RouterLink e permette di creare form nello stile “template-driven” (cioè il modo che abbiamo sempre usato in AngularJS). E’ stato introdotto un nuovo tipo di form chiamato “reactive” o “model-driven“, ovvero form create dinamicamente a partire da un modello di metadati che le descrivono. Da tenere sott’occhio perché evolverà parecchio nelle prossime versioni di Angular
    ReactiveFormsModule // abilita ngModel e RouterLink e permette di creare form nello stile “template-driven” (cioè il modo che abbiamo sempre usato in AngularJS). E’ stato introdotto un nuovo tipo di form chiamato “reactive” o “model-driven“, ovvero form create dinamicamente a partire da un modello di metadati che le descrivono. Da tenere sott’occhio perché evolverà parecchio nelle prossime versioni di Angular
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
