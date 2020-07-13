var stat = 886;
var stat1 = 409;
var stat2 = 700;

function count(countTo, countContainer, parentContainer) {
  var i = 0;
  var interval = setInterval(function () {
    if (i === countTo) clearInterval(interval);
    $(countContainer).html(i++);
    $(parentContainer).css("width", +(i / 10) + "%");
    $(parentContainer).css("background", "#fc" + i / 10 + "1f5b");
  }, 10);
}

$(document).ready(function () {
  count(stat, ".track", ".graph1");
  count(stat1, ".sick", ".graph2");
  count(stat2, ".money", ".graph3");

  $(".addMember").click(function () {
    $("#newMember").css("left", "0%");
  });
  $(".close").click(function () {
    $("#newMember").css("left", "100%");
  });
});
