import * as cooperationApi from '../../api/cooperation';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {

};

const getters = {

};

const mutations = {

};

const actions = {
  saveCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.saveCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  updateCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.updateCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listOwnCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listOwnCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listManageCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listManageCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listJoinCooperation({ commit }, formData) {
    return new Promise((resolve, reject) => {
      cooperationApi.listJoinCooperation(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listCooperationTree({ commit }, cooperationId) {
    return new Promise((resolve, reject) => {
      cooperationApi.listCooperationTree(cooperationId).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  getCooperation({ commit }, cooperationId) {
    return new Promise((resolve, reject) => {
      cooperationApi.getCooperation(cooperationId).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  deleteCooperation({ commit }, cooperationId) {
    return new Promise((resolve, reject) => {
      cooperationApi.deleteCooperation(cooperationId).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
};

export default {
  state,
  getters,
  mutations,
  actions,
};
