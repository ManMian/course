<template>
  <div>
    <p/>

    <!--data-toggle="modal" data-target="#form-modal"-->
      <button v-on:click="add()" class="btn btn-white btn-default btn-round" >
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
    &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    <p/>

<!--    v-bind:itemCount="8" 设置显示几个选择页数按钮-->
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>名称</th>
        <th>课程ID</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="chapter in chapters">
        <td>{{chapter.id}}</td>
        <td>{{chapter.name}}</td>
        <td>{{chapter.courseId}}</td>
        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button class="btn btn-xs btn-success">
              <i class="ace-icon fa fa-check bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>

            <button class="btn btn-xs btn-warning">
              <i class="ace-icon fa fa-flag bigger-120"></i>
            </button>
          </div>

          <div class="hidden-md hidden-lg">
            <div class="inline pos-rel">
              <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
              </button>

              <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                <li>
                  <a href="#" class="tooltip-info" data-rel="tooltip" title="View">
                                    <span class="blue">
                                      <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                    </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                    <span class="green">
                                      <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                    </span>
                  </a>
                </li>

                <li>
                  <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                    <span class="red">
                                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </span>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" class="form-control" placeholder="名称">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程ID</label>
                <div class="col-sm-10">
                  <input v-model="chapter.courseId" class="form-control" placeholder="课程ID">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button" class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    name: 'chapter',
    components: {Pagination},
    data: function() {
      return{
        //用于映射表单数据
        chapter: {},

        chapters: []
      }
  },
    mounted: function () {
      let _this = this;
      //设置每页显示的条数
      _this.$refs.pagination.size=5;
      _this.list(1);
      // sidebar激活样式方法一


      // this.$parent.activeSidebar("business-chapter-sidebar");
    },
    methods: {
      /**
       * 点击【新增】
       */
      add() {
      let _this = this;
         _this.chapter = {};
        $("#form-modal").modal("show");
      },
      /**
       * 列表查询
       */
      list(page) {
        let _this = this;
        _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list',{
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response)=>{
          // console.log("查询大章列表结果：", response);
          let resp = response.data;
          _this.chapters = resp.content.list;
          // console.log(resp,resp.content,resp.content.list);
          //重新渲染组件
          _this.$refs.pagination.render(page, resp.content.total)
        })
      }, /**
       * 保存
       */
      save(page) {
        let _this = this;
        _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/save',
          _this.chapter,
        ).then((response)=>{
          // console.log("保存大章列表结果：", response);
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            // Toast.success("保存成功！");
          } else {
            // Toast.warning(resp.message)
          }
        })
      }
    }
  }
</script>