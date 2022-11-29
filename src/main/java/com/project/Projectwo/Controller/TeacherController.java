package com.project.Projectwo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@PreAuthorize("hasRole('ROLE_TEACHER')")
public class TeacherController {

}
