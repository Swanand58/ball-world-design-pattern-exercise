# Ball World Assignment

## Overview

This is the README file for the "Ball World" assignment. The goal of this project is to create a web application that draws random-sized balls at random locations on a canvas. The application follows the MVC programming paradigm and utilizes various design patterns to structure the code.

## Model

### Concrete Balls

- The model includes at least 10 different balls, each with unique behaviors.
- Each ball resides in the `edu.rice.comp504.model.ball` package.
- The constructor for each ball takes a random size, a random location, a random velocity, and a color.
- Balls implement the `PropertyChangeListener` interface, and each must implement the `propertyChange` method.
- At least one ball moves straight horizontally, and there is at least one rotating ball.
- The model uses design patterns where appropriate.

### Ball Behavior

- Balls are created at random locations with a random radius and a random velocity.
- The magnitude of the velocity is not impacted by the ball's interactions in the Ball World.
- Balls bounce off walls if a collision is detected.
- If a ball collides with the left or right wall, the x component of the velocity is negated.
- If a ball collides with the top or bottom wall, the y component of the velocity is negated.

### Resilience and Fault Tolerance

- The model responds gracefully when encountering unrecognized REST GET requests.

## Controller

- The `BallWorldController` processes GET and POST requests and returns the JSON representation of all concrete ball objects in the Ball World.
- Endpoints include:
  - `POST /load` to load a specific ball into the ball world.
  - `GET /update` to update every ball in the ball world.
  - `GET /clear` to remove all balls in the ball world.
  - `POST /canvas/dims` to set the canvas dimensions.
  - `GET /ballworld` redirects users back to the home page.
- The controller is resilient and responds gracefully to unrecognized REST GET requests.

## View

- Updates to Ball World happen every 0.1 seconds.
- Users can clear all balls from the Ball World canvas.
- The design is user-friendly, allowing users to create and remove 10 unique balls.
- Balls are drawn based on the JSON data received from the controller.

## Unit Tests

- Unit tests cover:
  - Requesting a ball type with the `loadBall` method creates the expected ball type.
  - An update (for each ball type) with an expected location moves the ball to the expected location.
  - Adding a ball to the Ball World increases the number of balls by 1.
  - Clearing the Ball World sets the number of balls to 0.

## Usage

1. Clone the repository.
2. Run the application using BallWorldController.java file.
