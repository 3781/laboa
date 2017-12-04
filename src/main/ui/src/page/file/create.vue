<template>
  <el-row type="flex" justify="center">
    <el-col :xs="24" :sm="18" :md="12" :lg="8" :xl="6">
      <el-upload
        ref="upload"
        action="/api/file"
        :multiple="true"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="files"
        :auto-upload="false"
        :with-credentials="true">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        <!--<div slot="tip" class="el-upload__tip">文件不能超过500kb</div>-->
      </el-upload>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: 'fileCreate',
    data() {
      return {
        files: [],
      };
    },
    methods: {
      upload(param) {
        console.log(param);
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
