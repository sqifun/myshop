/**
 * 函数对象
 * @constructor
 */
var Validate = function () {

    /**
     * 初始化jquery validation
     */
    let handlerInitValidate = function () {

        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });

    };

    /**
     * 增加自定义验证规则
     */
    let handleInitCustomValidate = function () {
        $.validator.addMethod("mobile", function(value, element) {
            let length = value.length;
            let mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    return {
        /**
         * 初始化
         */
        init: function () {
            handleInitCustomValidate();
            handlerInitValidate();
        }
    }

}();


$(document).ready(function () {
    Validate.init();
})