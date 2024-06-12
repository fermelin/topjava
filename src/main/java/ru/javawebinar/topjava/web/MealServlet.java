package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private List<MealDto> toDtoList(List<Meal> meals, int caloriesDayLimit) {
        return toDtoList(meals, LocalTime.MIN, LocalTime.MAX, caloriesDayLimit);
    }

    private List<MealDto> toDtoList(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesDayLimit) {
        return filteredByStreams(meals, startTime, endTime, caloriesDayLimit);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        request.setAttribute("meals", toDtoList(getHardcodedListOfMeals(), DEFAULT_CALORIES_LIMIT));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
