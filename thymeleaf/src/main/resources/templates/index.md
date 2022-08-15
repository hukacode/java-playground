Title: [(${title})]
[# th:object="${employee}"]
Name: [(*{name})]
Birthdate: [(*{birthDate})]

Skills: [# th:each="item : *{skills}"]
  - [(${item})]
  [/]
[/]
