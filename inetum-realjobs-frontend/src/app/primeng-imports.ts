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
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {TabViewModule} from "primeng/tabview";

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
  TabViewModule,
];

@NgModule({
  imports: modules,
  exports: modules,
})
export class PrimengImportsModule {
}
