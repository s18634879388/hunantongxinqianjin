$('.topBtn').click(function () {
    $('.cover').show();
});
$('.coverClose').click(function () {
    $('.cover').hide();
});
var numI = $('a i');
$.each(numI, function (i, b) {
    $(this).text(i + 1)
})