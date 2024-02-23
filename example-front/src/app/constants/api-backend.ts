const base = 'http://localhost:8090';

export const apiRequests = {
  authEndpoint: {
    login: `${base}/login`,
  },
  products: {
    getAll: `${base}/products`,
  },
};
