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
import {CheckboxModule} from "primeng/checkbox";
import {RippleModule} from "primeng/ripple";
import {StyleClassModule} from "primeng/styleclass";
import {ChipModule} from "primeng/chip";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ConfirmPopupModule} from "primeng/confirmpopup";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {TableModule} from "primeng/table";


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
  DividerModule,
  ScrollPanelModule,
  CheckboxModule,
  RippleModule,
  StyleClassModule,
  ChipModule,
  InputTextareaModule,
  ConfirmPopupModule,
  ConfirmDialogModule,
  ProgressSpinnerModule,
  TableModule
];

@NgModule({
  imports: modules,
  exports: modules,
})
export class PrimengImportsModule {
}
