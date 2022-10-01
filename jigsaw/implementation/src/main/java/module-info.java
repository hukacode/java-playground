module java.playground.jigsaw.implementation {
  requires java.playground.jigsaw.api;

  provides dev.huka.playground.jigsaw.api.EmployeeService with
      dev.huka.playground.jigsaw.implementation.EmployeeHandler;
}
