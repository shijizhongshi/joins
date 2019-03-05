$(function() {

    function YFocus03(icon, focuswrap, number) {

        var timer = null;

        var i = 0;

        var y_wqjdLen = $(focuswrap).find("li").length;

        $(focuswrap).find("li").each(function() {

            $(icon).append("<a href='javascript:;'></a>")

        });

        $(icon).find("a:first").addClass("cur");

        timer = setInterval(function() {

            i++;

            if (i > y_wqjdLen - 1)

            {

                i = 0;

                focus_ul();

            }

            else

            {

                focus_ul();

            }

        },
        4000);

        $(icon).find("a").mouseenter(function() {

            i = $(this).index();

            focus_ul();

        });

        $(focuswrap).mouseover(function() {

            clearInterval(timer);

        });

        $(".std_left_span,.std_right_span").mouseover(function() {

            clearInterval(timer);

        });

        $(".std_left_span,.std_right_span").mouseleave(function() {

            timer = setInterval(function() {

                i++;

                if (i > y_wqjdLen - 1)

                {

                    i = 0;

                    focus_ul();

                }

                else

                {

                    focus_ul();

                }

            },
            4000);

        });

        $(focuswrap).mouseleave(function() {

            timer = setInterval(function() {

                i++;

                if (i > y_wqjdLen - 1)

                {

                    i = 0;

                    focus_ul();

                }

                else

                {

                    focus_ul();

                }

            },
            4000);

        });

        $(".std_right_span").click(function() {

            i++;

            if (i > y_wqjdLen - 1)

            {

                i = 0;

                focus_ul();

            }

            else

            {

                focus_ul();

            }

        });

        $(".std_left_span").click(function() {

            i--;

            if (i < 0)

            {

                i = y_wqjdLen - 1;

                focus_ul();

            }

            else

            {

                focus_ul();

            }

        });

        function focus_ul() {

            $(focuswrap).find("ul").stop(true).animate({
                "marginLeft": -(number * i)
            },
            500);

            $(icon).find("a").eq(i).addClass("cur").siblings().removeClass("cur");

        };

    };

    YFocus03(".std_icon", ".std_xgbox", "990");

});