pushd . & mkdir database & cd database & start mongod --dbpath . & popd
pushd . & cd app-server & start npm start & popd

