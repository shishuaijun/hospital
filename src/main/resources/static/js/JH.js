var Jh = {
    Config: {
        tableCls: "form-list",
        tdCls: "form-text",
        tdCls2: "single",
        ulCls: "tag-list",
        layCls: "layout-list",
        min: "min",
        mintext: "\u6536\u8d77",
        max: "max",
        maxtext: "\u5c55\u5f00",
        close: "close",
        closetext: "\u5173\u95ed",
        refreshtext: "\u5237\u65b0",
        refresh: "refresh",
        _groupItemContent: "itemContent",
        _groupItemHead: "itemHeader",
        _groupWrapperClass: "groupWrapper",
        _groupItemClass: "groupItem"
    }
};
Jh.Layout = function () {
    return {
        location: {left: "portal_l", center: "portal_m", right: "portal_r"},
        locationId: {left: "#portal_l", center: "#portal_m", right: "#portal_r"},
        layoutCss: {"0": "1:3", 1: "3:1", 2: "1:2:1", 3: "1:1:2", 4: "2:1:1", 5: "1:1:1"},
        layoutText: {
            "0": "w250 w750 wnone",
            1: "w750 w250 wnone",
            2: "w250 w500 w250",
            3: "w250 w250 w500",
            4: "w500 w250 w250",
            5: "w250 w250 w250"
        }
    }
}();
Jh.Util = {
    format: function (a, b) {
        for (var c in b) a = a.replace(RegExp("{" + c + "}", "g"), b[c]);
        return a
    }, refresh: function () {
        $("#" + Jh.Layout.left, "#" + Jh.Layout.center, "#" + Jh.Layout.right).sortable("refresh")
    }, toBody: function (a) {
        $("body").append(a)
    }
};
Jh.fn = function (a) {
    return a = {
        init: function (b) {
            a._ele = {};
            a._create();
            a._createWrap(b);
            a._bindEvent()
        }, _create: function () {
            var b = $("<div id='header'/>");
            a.box = b;
            Jh.Util.toBody(b)
        }, _createWrap: function (b) {
            var c = a._createTable(Jh.Config.tableCls);
            a._ele.table = c;
            a._createModuleList(b);
            a._createActionButton();
            a._addPanel(c)
        }, _createTable: function (b) {
            b = $("<table/>").addClass(b);
            $("<tbody/>").append(a._createLayoutTr()).append(a._createBaseTr()).append(a._createActionTr()).appendTo(b);
            return b
        }, _createBaseTr: function () {
            var b = a._createTd(Jh.Config.tdCls2),
                c = $("<tr>").append(a._createTd(Jh.Config.tdCls, "\u529f\u80fd\u6a21\u5757\u8bbe\u7f6e\uff1a")).append(b);
            a._ele.mtd = b;
            return c
        }, _createActionTr: function () {
            var b = a._createTd(Jh.Config.tdCls2), c = $("<tr>").append(a._createTd(Jh.Config.tdCls)).append(b);
            a._ele.atd = b;
            return c
        }, _createLayoutTr: function () {
            var b = a._createTd(Jh.Config.tdCls2);
            $("<div/>").addClass(Jh.Config.layCls).append(a._createA("1:3")).append(a._createA("3:1")).append(a._createA("1:1:2")).append(a._createA("1:2:1")).append(a._createA("2:1:1")).append("<a href='javascript:void(0);' class='active' rel='1:1:1'>\u9ed8\u8ba4</a>").appendTo(b);
            var c = $("<tr>").append(a._createTd(Jh.Config.tdCls, "\u5e03\u5c40\u8bbe\u7f6e\uff1a")).append(b);
            a._ele.layoutTd = b;
            return c
        }, _createModuleList: function (b) {
            var c = $("<ul/>").addClass(Jh.Config.ulCls);
            a._createLis(b.appL, c);
            a._createLis(b.appM, c);
            a._createLis(b.appR, c);
            a._ele.ul = c;
            c.appendTo(a._ele.mtd)
        }, _createActionButton: function () {
            var b = $("<a class='button b' href='#' >\u6dfb\u52a0\u6a21\u5757</a>"),
                c = $("<a class='button b' href='#' >\u4fdd\u5b58\u914d\u7f6e</a>");
            a._ele.atd.append(b).append(c);
            a._bindAdd(b);
            a._bindSave(c)
        }, _createLis: function (b, c) {
            $.each(b, function (b, d) {
                c.append(a._createLi(b, d))
            })
        }, _createA: function (a) {
            return $("<a href='javascript:void(0);' rel='" + a + "'>" + a + "</a>")
        }, _createLi: function (a, c) {
            return $("<li/>").append("<a href='#' rel='" + a + "'>" + c + "</a>").append("<span class='ok'></span>")
        }, _createTd: function (a, c) {
            var e = $("<td>").addClass(a);
            void 0 != c && e.text(c);
            return e
        }, _addPanel: function (b) {
            a.box.append(b)
        }, _bindAdd: function (b) {
            b.click(function () {
                $.fallr("show", {
                    buttons: {
                        button1: {
                            text: "\u786e\u5b9a", onclick: function () {
                                var b = $(this).children("form"), e = b.children("#modulename").val(),
                                    d = b.children("#modulekey").val(),
                                    b = b.children("input[name='modulelayout']:checked").val(),
                                    b = "left" == b ? $("#" + Jh.Layout.location.left) : "center" == b ? $("#" + Jh.Layout.location.center) : $("#" + Jh.Layout.location.right);
                                a._ele.ul.append(a._createLi(d, e));
                                b.append(Jh.Portal._createPortalOne(d, e));
                                $.fallr("hide")
                            }
                        }, button2: {text: "\u53d6\u6d88"}
                    },
                    content: '<form style="margin-left:20px"><p>\u6a21\u5757\u540d\uff1a</p><input type="text" size="15" id="modulename" /><p>\u6a21\u5757Code\uff1a</p><input type="text" size="15" id="modulekey" /><p>\u6a21\u5757\u4f4d\u7f6e\uff1a</p>\u5de6:<input type="radio" name="modulelayout" checked="checked" value="left"/>&nbsp&nbsp\u4e2d:<input type="radio" name="modulelayout" value="center"/>&nbsp&nbsp\u53f3:<input type="radio" name="modulelayout" value="right"/></form>',
                    icon: "add",
                    position: "center"
                })
            })
        }, _bindSave: function (a) {
            a.click(function () {
                var a = $("#" + Jh.Layout.location.left).sortable("toArray"),
                    b = $("#" + Jh.Layout.location.center).sortable("toArray"),
                    d = $("#" + Jh.Layout.location.right).sortable("toArray"), f = "";
                $("." + Jh.Config.layCls + " a").each(function () {
                    $(this).hasClass("active") && (f = $(this).attr("rel"))
                });
                "1:1:1" == f && (f = "\u9ed8\u8ba4");
                $.fallr("show", {
                    content: "<p>left:[" + a + "]</p><p>center:[" + b + "]</p><p>right[" + d + "]</p><p>\u5f53\u524d\u5e03\u5c40:" + f + "</p>",
                    position: "center"
                })
            })
        }, _bindEvent: function () {
            a._moduleLiClick();
            a._layoutAClick()
        }, _moduleLiClick: function () {
            $("." + Jh.Config.ulCls + " li").live("click", function () {
                var a = $(this), c = a.find("a").attr("rel"), c = $("#" + c), a = a.find(".ok");
                a.is(":visible") ? (a.hide(), c.hide()) : (a.show(), c.show());
                Jh.Util.refresh()
            })
        }, _layoutAClick: function () {
            $("." + Jh.Config.layCls + " a").click(function () {
                var b = $(this), c = b.attr("rel");
                a._ToLayout(c);
                b.addClass("active").siblings().removeClass("active")
            })
        }, _ToLayout: function (a) {
            var c = Jh.Layout.layoutText, e = Jh.Layout.locationId, d = 0, f = "";
            $.each(Jh.Layout.layoutCss, function (c, j) {
                a == j && (d = c)
            });
            $.each(e, function (a, b) {
                var g = $(b), h = c[d].split(/\s+/);
                switch (a) {
                    case "left":
                        a = 0;
                        break;
                    case "center":
                        a = 1;
                        break;
                    case "right":
                        a = 2
                }
                "wnone" == h[a] && (f = g.sortable("toArray"), $.each(f, function (a, b) {
                    $("#" + Jh.Layout.location.left).append($("#" + b))
                }), g.empty());
                g.removeClass("w250 w750 w500 wnone").addClass(h[a])
            })
        }
    }
}();
Jh.Portal = function (a) {
    var b = "<div id='" + Jh.Layout.location.left + "' class='" + Jh.Config._groupWrapperClass + " w250'/>",
        c = "<div id='" + Jh.Layout.location.center + "' class='" + Jh.Config._groupWrapperClass + " w250'/>",
        e = "<div id='" + Jh.Layout.location.right + "' class='" + Jh.Config._groupWrapperClass + " w250'/>",
        d = "<div id='{key}' class='" + Jh.Config._groupItemClass + "'/>",
        f = "<div class='" + Jh.Config._groupItemHead + "'><h3>{name}</h3></div>",
        i = "<div class='" + Jh.Config._groupItemContent + "'/>";
    return a = {
        init: function (b) {
            a._create();
            a._bindData(b);
            a._bindEvent()
        }, _create: function () {
            a.box = $("<div id='portal'></div>");
            a._elements = {};
            a._createModulesWrap();
            Jh.Util.toBody(a.box)
        }, _bindData: function (b) {
            $.each(b, function (b, c) {
                a._createPortal(b, c)
            })
        }, _createModulesWrap: function () {
            a._elements.m_l = $(b);
            a._elements.m_m = $(c);
            a._elements.m_r = $(e);
            a._addPanel(a._elements.m_l);
            a._addPanel(a._elements.m_m);
            a._addPanel(a._elements.m_r)
        }, _addPanel: function (b) {
            a.box.append(b)
        }, _createPortal: function (b, c) {
            var d;
            switch (b) {
                case "appL":
                    d = a._elements.m_l;
                    break;
                case "appM":
                    d = a._elements.m_m;
                    break;
                case "appR":
                    d = a._elements.m_r
            }
            $.each(c, function (b, c) {
                d.append(a._createPortalOne(b, c))
            })
        }, _createPortalOne: function (b, c) {
            var e = a._createItemHeader(c), f = a._createItemContent();
            return $(Jh.Util.format(d, {key: b})).append(e).append(f)
        }, _createItemHeader: function (b) {
            var b = $(Jh.Util.format(f, {name: b})), c = a._createDiv("action").hide().appendTo(b);
            a._createA(Jh.Config.refresh, Jh.Config.refreshtext, !0).appendTo(c);
            a._createA(Jh.Config.min, Jh.Config.mintext, !0).appendTo(c);
            a._createA(Jh.Config.max, Jh.Config.maxtext, !1).appendTo(c);
            a._createA(Jh.Config.close, Jh.Config.closetext, !0).appendTo(c);
            b.hover(function () {
                $(this).find(".action").show()
            }, function () {
                $(this).find(".action").hide()
            });
            return b
        }, _createItemContent: function () {
            var a = $(i);
            $("<ul style='width:250px;'><li>xiaofanV587</li><li>xiaofanV587</li><li>xiaofanV587</li><li>xiaofanV587</li></ul>").appendTo(a);
            return a
        }, _createDiv: function (a) {
            return $("<div/>").addClass(a)
        }, _createA: function (a, b, c) {
            a = $("<a href='javascript:void(0);' class='" +
                a + "' title='" + b + "'/>");
            c || a.hide();
            return a
        }, _eventMin: function () {
            $("." + Jh.Config.min).live("click", function () {
                var a = $(this), b = a.parent().parent().parent();
                b.find("." + Jh.Config._groupItemContent).hide();
                b.find("." + Jh.Config.max).show();
                a.hide()
            })
        }, _eventMax: function () {
            $("." + Jh.Config.max).live("click", function () {
                var a = $(this), b = a.parent().parent().parent();
                b.find("." + Jh.Config._groupItemContent).show();
                b.find("." + Jh.Config.min).show();
                a.hide()
            })
        }, _eventRemove: function () {
            $("." + Jh.Config.close).live("click", function () {
                $(this).parent().parent().parent().fadeOut("500", function () {
                    var a = $(this), b = a.attr("id"), b = $(".tag-list").find("a[rel='" + b + "']");
                    a.remove();
                    b.parent().remove()
                })
            })
        }, _eventRefresh: function () {
            $("." + Jh.Config.refresh).live("click", function () {
                $(this).parent().parent().parent().find("ul").empty().append("<li>\u5237\u65b0\u4e86</li>")
            })
        }, _eventSortable: function () {
            $("." + Jh.Config._groupWrapperClass).sortable({
                connectWith: "." + Jh.Config._groupWrapperClass,
                opacity: "0.6",
                dropOnEmpty: !0
            }).disableSelection()
        }, _bindEvent: function () {
            a._eventSortable();
            a._eventRefresh();
            a._eventRemove();
            a._eventMax();
            a._eventMin()
        }
    }
}();