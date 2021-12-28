import {ApplicationStatus} from "./application-status.enum";
import {JobSeeker} from "./job-seeker";

export class Application {
  id: number;
  status: ApplicationStatus | null;
  motivation: string | null;
  jobSeeker: JobSeeker;
}
