import {NgModule} from "@angular/core";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {PanelModule} from "primeng/panel";
import {CardModule} from "primeng/card";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";
import {FileUploadModule} from "primeng/fileupload";
import {RadioButtonModule} from "primeng/radiobutton";
import {MessagesModule} from "primeng/messages";
import {MessageModule} from "primeng/message";
import {ToastModule} from "primeng/toast";
import {InputMaskModule} from "primeng/inputmask";
import {PasswordModule} from "primeng/password";
import {DividerModule} from "primeng/divider";
import {ScrollPanelModule} from "primeng/scrollpanel";
import {FieldsetModule} from 'primeng/fieldset';
import {InputNumberModule} from 'primeng/inputnumber';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ProgressSpinnerModule} from "primeng/progressspinner";


const modules = [
  ButtonModule,
  InputTextModule,
  PanelModule,
  CardModule,
  DropdownModule,
  CalendarModule,
  RadioButtonModule,
  MessagesModule,
  MessageModule,
  FileUploadModule,
  ToastModule,
  InputMaskModule,
  PasswordModule,
  PanelModule,
  DividerModule,
  ScrollPanelModule,
  ProgressSpinnerModule,
  FieldsetModule,
  InputNumberModule,
  ConfirmDialogModule,
];

@NgModule({
  imports: modules,
  exports: modules,
})
export class PrimengImportsModule {
}
