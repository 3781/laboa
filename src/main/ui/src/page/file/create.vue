<template>
  <el-row type="flex" justify="center">
    <el-col :xs="24" :sm="18" :md="12" :lg="8" :xl="6">
      <el-upload
        ref="upload"
        action="/api/file"
        :multiple="true"
        :on-preview="handlePreview"
        :auto-upload="false"
        :with-credentials="true"
        :on-success="handleSuccessUpload"
        :on-error="handleErrorUpload">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        <!--<div slot="tip" class="el-upload__tip">文件不能超过500kb</div>-->
      </el-upload>
    </el-col>
    <el-dialog v-loading.lock="loading"
      title="添加文件备注"
      :visible.sync="dialogVisible"
      width="30%">
      <el-input type="textarea" :rows="4" placeholder="请输入文件备注" v-model="updateFile.remark"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" >取 消</el-button>
        <el-button type="primary" @click="addFileRemark" >确 定</el-button>
      </span>
    </el-dialog>
  </el-row>
</template>
<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'fileCreate',
    data() {
      return {
        loading: false,
        dialogVisible: false,
        updateFile: {
          fileId: null,
          remark: null,
        },
      };
    },
    methods: {
      ...mapActions(['update']),
      handleSuccessUpload(response, file) {
        if (response.success) {
          const tmp = file;
          tmp.info = { fileId: response.info, remark: '' };
          this.$notify({
            message: `文件${file.name}上传成功`,
            type: 'info',
            position: 'bottom-right',
            offset: 40,
          });
        } else {
          this.$notify({
            message: response.info,
            type: 'error',
            position: 'bottom-right',
            offset: 40,
          });
        }
      },
      handleErrorUpload(err, file) {
        this.$notify({
          message: `文件${file.name}上传失败，请稍后再试`,
          type: 'error',
          position: 'bottom-right',
          offset: 40,
        });
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      handlePreview(file) {
        if (file.response) {
          this.updateFile = file.info;
          this.dialogVisible = true;
        }
      },
      addFileRemark() {
        this.loading = true;
        this.update(this.updateFile).then(() => {
          this.$notify({
            message: '更新成功',
            type: 'info',
            position: 'bottom-right',
            offset: 40,
          });
          this.loading = false;
          this.dialogVisible = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
